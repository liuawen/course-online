<template>
  <div>
    <button type="button" v-on:click="selectFile()" class="btn btn-white btn-default btn-round">
      <i class="ace-icon fa fa-upload"></i>
      {{text}}
    </button>
    <input class="hidden" type="file" ref="file" v-on:change="uploadFile()" v-bind:id="inputId+'-input'">
  </div>
</template>

<script>
  export default {
    name: 'big-file',
    props: {
      text: {
        default: "上传大文件"
      },
      inputId: {
        default: "file-upload"
      },
      suffixs: {
        default: []
      },
      use: {
        default: ""
      },
      shardSize: {
        default: 50 * 1024
      },
      url: {
        default: "oss-append"
      },
      afterUpload: {
        type: Function,
        default: null
      },
    },
    data: function () {
      return {
      }
    },
    methods: {
      uploadFile () {
        let _this = this;
        let formData = new window.FormData();
        let file = _this.$refs.file.files[0];

        console.log(JSON.stringify(file));
        /*
          name: "test.mp4"
          lastModified: 1901173357457
          lastModifiedDate: Tue May 27 2099 14:49:17 GMT+0800 (中国标准时间) {}
          webkitRelativePath: ""
          size: 37415970
          type: "video/mp4"
        */

        // 生成文件标识，标识多次上传的是不是同一个文件
        let key = hex_md5(file.name + file.size + file.type);
        let key10 = parseInt(key, 16);
        let key62 = Tool._10to62(key10);
        console.log(key, key10, key62);
        console.log(hex_md5(Array()));
        /*
          d41d8cd98f00b204e9800998ecf8427e
          2.8194976848941264e+38
          6sfSqfOwzmik4A4icMYuUe
         */

        // 判断文件格式
        let suffixs = _this.suffixs;
        let fileName = file.name;
        let suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();
        let validateSuffix = false;
        for (let i = 0; i < suffixs.length; i++) {
          if (suffixs[i].toLowerCase() === suffix) {
            validateSuffix = true;
            break;
          }
        }
        if (!validateSuffix) {
          Toast.warning("文件格式不正确！只支持上传：" + suffixs.join(","));
          $("#" + _this.inputId + "-input").val("");
          return;
        }

        // 文件分片
        // let shardSize = 10 * 1024 * 1024;    //以10MB为一个分片
        // let shardSize = 50 * 1024;    //以50KB为一个分片
        let shardSize = _this.shardSize;
        let shardIndex = 1;		//分片索引，1表示第1个分片
        let size = file.size;
        let shardTotal = Math.ceil(size / shardSize); //总片数

        let param = {
          'shardIndex': shardIndex,
          'shardSize': shardSize,
          'shardTotal': shardTotal,
          'use': _this.use,
          'name': file.name,
          'suffix': suffix,
          'size': file.size,
          'key': key62
        };

        _this.check(param);
      },



      /**
       * 检查文件状态，是否已上传过？传到第几个分片？
       */
      check (param) {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/file/admin/check/' + param.key).then((response)=>{
          let resp = response.data;
          if (resp.success) {
            let obj = resp.content;
            if (!obj) {
              param.shardIndex = 1;
              console.log("没有找到文件记录，从分片1开始上传");
              _this.upload(param);
            } else if (obj.shardIndex === obj.shardTotal) {
              // 已上传分片 = 分片总数，说明已全部上传完，不需要再上传
              Toast.success("文件极速秒传成功！");
              _this.afterUpload(resp);
              $("#" + _this.inputId + "-input").val("");
            }  else {
              param.shardIndex = obj.shardIndex + 1;
              console.log("找到文件记录，从分片" + param.shardIndex + "开始上传");
              _this.upload(param);
            }
          } else {
            Toast.warning("文件上传失败");
            $("#" + _this.inputId + "-input").val("");
          }
        })
      },

      /**
       * 将分片数据转成base64进行上传
       */
      upload (param) {
        let _this = this;
        let shardIndex = param.shardIndex;
        let shardTotal = param.shardTotal;
        let shardSize = param.shardSize;
        let fileShard = _this.getFileShard(shardIndex, shardSize);
        // 将图片转为base64进行传输
        let fileReader = new FileReader();

        Progress.show(parseInt((shardIndex - 1) * 100 / shardTotal));
        fileReader.onload = function (e) {
          let base64 = e.target.result;
          // console.log("base64:", base64);

          param.shard = base64;

          _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/' + _this.url, param).then((response) => {
            let resp = response.data;
            console.log("上传文件成功：", resp);
            Progress.show(parseInt(shardIndex * 100 / shardTotal));
            if (shardIndex < shardTotal) {
              // 上传下一个分片
              param.shardIndex = param.shardIndex + 1;
              _this.upload(param);
            } else {
              Progress.hide();
              _this.afterUpload(resp);
              $("#" + _this.inputId + "-input").val("");
            }
          });
        };
        fileReader.readAsDataURL(fileShard);
      },

      getFileShard (shardIndex, shardSize) {
        let _this = this;
        let file = _this.$refs.file.files[0];
        let start = (shardIndex - 1) * shardSize;	//当前分片起始位置
        let end = Math.min(file.size, start + shardSize); //当前分片结束位置
        let fileShard = file.slice(start, end); //从文件中截取当前的分片数据
        return fileShard;
      },

      selectFile () {
        let _this = this;
        $("#" + _this.inputId + "-input").trigger("click");
      }
    }
  }
</script>
