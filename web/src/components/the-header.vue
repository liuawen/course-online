<template>
  <header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container">
        <a class="navbar-brand" href="#">
          <i class="ace-icon fa fa-video-camera"></i>&nbsp;甲蛙课程
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <router-link class="nav-link" to="/">主页</router-link>
            </li>
            <li class="nav-item active">
              <router-link class="nav-link" to="/list">全部课程</router-link>
            </li>
            <li class="nav-item dropdown active">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                更多
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="#">关于我们</a>
                <a class="dropdown-item" href="#">渠道合作</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">更多信息</a>
              </div>
            </li>
          </ul>
          <span v-show="loginMember.id" class="text-white pr-3">您好：{{loginMember.name}}</span>
          <button v-show="loginMember.id" v-on:click="logout()" class="btn btn-outline-light my-2 my-sm-0">退出登录</button>
          <button v-show="!loginMember.id" v-on:click="openLoginModal()" class="btn btn-outline-light my-2 my-sm-0">登录/注册</button>
        </div>
      </div>
    </nav>

    <the-login ref="loginComponent"></the-login>
  </header>
</template>

<script>

  import TheLogin from "./login";
  export default {
    name: 'theHeader',
    components: {TheLogin},
    data: function () {
      return {
        loginMember: {}
      }
    },
    mounted() {
      let _this = this;
      _this.loginMember = Tool.getLoginMember();
    },
    methods: {
      /**
       * 打开登录注册窗口
       */
      openLoginModal() {
        let _this = this;
        _this.$refs.loginComponent.openLoginModal();
      },

      setLoginMember(loginMember) {
        let _this = this;
        _this.loginMember = loginMember;
      },

      logout () {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/web/member/logout/' + _this.loginMember.token).then((response)=>{
          let resp = response.data;
          if (resp.success) {
            Tool.setLoginMember(null);
            _this.loginMember = {};
            Toast.success("退出登录成功");
            _this.$router.push("/");
          } else {
            Toast.warning(resp.message);
          }
        });
      },

    }
  }
</script>
