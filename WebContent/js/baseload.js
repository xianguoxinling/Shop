
  $(function load() {
    $('#header').load('/load/pdheader.html');
    $('#footer').load('/load/footer.html');
  });
  $(function iflogin() {
      var iflogin = document.getElementById('login');

      if (getCookie('username') != null) {
          iflogin.innerHTML = 
        	  '<a>' + '你好，' + getCookie('username') + '</a>' + 
        	  '<span> </span>' + 
        	  '<a href=\"/puckart/order/list.action\">我的订单</a>' +
        	  '<span> </span>' + 
        	  '<a href=\"/puckart/cart.action\">我的购物车</a>' +
        	  '<span> </span>' + 
        	  '<a href=\"/puckart/logout.action\">注销</a>';
      } else {
          iflogin.innerHTML = '<a href="/user/login.html">登录</a>' + '<span> </span>' + '<a href="/user/register.html">注册</a>';
      }
  });
  function GetQueryString(name) {
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
      var r = window.location.search.substr(1).match(reg);
      if (r != null) return (r[2]);
      return null;
  }
  // var pagenow = parseInt(GetQueryString("pagenow"));
