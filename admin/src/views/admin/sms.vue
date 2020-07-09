<template>
  <div>
    <p>
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>

    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>id</th>
        <th>手机号</th>
        <th>验证码</th>
        <th>用途</th>
        <th>生成时间</th>
        <th>用途</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="sms in smss">
        <td>{{sms.id}}</td>
        <td>{{sms.mobile}}</td>
        <td>{{sms.code}}</td>
        <td>{{SMS_USE | optionKV(sms.use)}}</td>
        <td>{{sms.at}}</td>
        <td>{{SMS_STATUS | optionKV(sms.status)}}</td>
      </tr>
      </tbody>
    </table>

  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  export default {
    components: {Pagination},
    name: "business-sms",
    data: function() {
      return {
        sms: {},
        smss: [],
        SMS_USE: SMS_USE,
        SMS_STATUS: SMS_STATUS,
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 5;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("business-sms-sidebar");

    },
    methods: {
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.sms = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(sms) {
        let _this = this;
        _this.sms = $.extend({}, sms);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/sms/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.smss = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);

        })
      },
    }
  }
</script>
