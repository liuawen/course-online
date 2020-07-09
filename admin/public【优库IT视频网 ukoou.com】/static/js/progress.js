Progress = {
  show: function (width) {
    let _this = this;
    _this.width = width;
    if ($("#progress-div").length > 0) {
      $(".progress").attr("data-percent", width);
      $(".progress-bar").width(width + "%");
    } else {
      let progressDiv = "<div id=\"progress-div\" style=\"z-index: 10011;\n" +
        "    position: fixed;\n" +
        "    padding: 10px;\n" +
        "    margin: 0px 0px 0px -150px;\n" +
        "    width: 300px;\n" +
        "    top: 40%;\n" +
        "    left: 50%;\n" +
        "    text-align: center;\n" +
        "    height: 45px;\n" +
        "    color: rgb(0, 0, 0);\n" +
        "    border: 3px solid rgb(170, 170, 170);\n" +
        "    background-color: rgb(255, 255, 255);\n" +
        "    cursor: wait;\"><div class=\"progress pos-rel\" data-percent=\"" + width + "%\"><div class=\"progress-bar\"></div></div></div>";
      $("#progress-div").remove();
      $("body").append(progressDiv);

      // 背景遮罩
      $("body").append($("<div id=\"progress-overlay\" style=\"z-index: 10010;\n" +
        "  border: none;\n" +
        "  margin: 0px;\n" +
        "  padding: 0px;\n" +
        "  width: 100%;\n" +
        "  height: 100%;\n" +
        "  top: 0px;\n" +
        "  left: 0px;\n" +
        "  background-color: rgb(0, 0, 0);\n" +
        "  opacity: 0.6;\n" +
        "  cursor: wait;\n" +
        "  position: fixed;\"></div>"));
      $(".progress-bar").width(width + "%");
    }

  },

  hide: function () {
    setTimeout(function () {
      $("#progress-div").remove();
      $("#progress-overlay").remove();
    }, 1000);
  }
};
