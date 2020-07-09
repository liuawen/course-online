Pattern = {
  // 用户名正则，2到16位（字母，数字，下划线）
  loginNamePattern: /^[a-zA-Z0-9_]{6,16}$/,

  // 昵称正则，6到20位中文，字母，数字，下划线
  namePattern: /^[\w\u4e00-\u9fa5]{2,20}$/,

  // 强密码强度正则，最少8位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符
  passwordStrongPattern: /^.*(?=.{8,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/,

  // 弱密码强度正则，最少6位，包括至少1字母，1个数字
  passwordWeakPattern: /^.*(?=.{6,})(?=.*\d)(?=.*[A-Za-z]).*$/,

  // 手机号正则，11位数字，1开头
  mobilePattern: /^1\d{10}$/,

  // 图片验证码正则，4位字母，数字
  imageCodePattern: /^[a-zA-Z0-9]{4}$/,

  // 手机验证码正则，6位数字
  mobileCodePattern: /^[0-9]{6}$/,

  validateLoginName: function (str) {
    if (Tool.isEmpty(str)) {
      return false;
    }
    return this.loginNamePattern.test(str);
  },

  validateName: function (str) {
    if (Tool.isEmpty(str)) {
      return false;
    }
    return this.namePattern.test(str);
  },

  validatePasswordStrong: function (str) {
    if (Tool.isEmpty(str)) {
      return false;
    }
    return this.passwordStrongPattern.test(str);
  },

  validatePasswordWeak: function (str) {
    if (Tool.isEmpty(str)) {
      return false;
    }
    return this.passwordWeakPattern.test(str);
  },

  validateMobile: function (str) {
    if (Tool.isEmpty(str)) {
      return false;
    }
    return this.mobilePattern.test(str);
  },

  validateImageCode: function (str) {
    if (Tool.isEmpty(str)) {
      return false;
    }
    return this.imageCodePattern.test(str);
  },

  validateMobileCode: function (str) {
    if (Tool.isEmpty(str)) {
      return false;
    }
    return this.mobileCodePattern.test(str);
  }
};
