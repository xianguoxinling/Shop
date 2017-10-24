$(function pagenow() {
    var pagenow = parseInt(GetQueryString("pageNow"));
    var node = parseInt(GetQueryString("node"));

    var v1 = parseInt(node / 10000);
    var v4 = (parseInt(node / 100)) - 100 * v1;
    var v2 = v4 - 1;
    var v3 = node - v1 * 10000 - v4 * 100 - 1;
    console.log("node:" + node);
    console.log("sm:" + typedata[v1].c[v2].d[v3].dt[0]);
    console.log("md:" + typedata[v1].c[v2].ct[0]);
    console.log("lg:" + typedata[v1].p[0]);
    console.log("data0:" + typedata[v1].c[v2].d[v3].dt[0]);
    console.log("data1:" + typedata[v1].c[v2].d[v3].dt[1]);
    console.log("data2:" + typedata[v1].c[v2].d[v3].dt[2]);

    $('#lg-node').html(typedata[v1].p[0]);
    $('#md-node').html(typedata[v1].c[v2].ct[0]);
    $('#sm-node').html(typedata[v1].c[v2].d[v3].dt[0]);
    $('#lg-node').attr('href', typedata[v1].p[2]);
    $('#md-node').attr('href', typedata[v1].c[v2].ct[2]);
    $('#sm-node').attr('href', typedata[v1].c[v2].d[v3].dt[2]);


    pagination();

    function pagination() {
        var Pagination = document.getElementById('pagination');
        if (pagenow == 2) {
            Pagination.innerHTML = '<li><a href="/puckart/searchtype.action?pageNow=1&node=' + node + '">«</a></li>' +
                '<li><a href="/puckart/searchtype.action?pageNow=1&node=' + node + '">1</a></li>' +
                '<li class="curent"><a href="/puckart/searchtype.action?pageNow=2&node=' + node + '">2</a></li>' +
                '<li><a href="/puckart/searchtype.action?pageNow=3&node=' + node + '">3</a></li>' +
                '<li><a href="/puckart/searchtype.action?pageNow=4&node=' + node + '">4</a></li>' +
                '<li><a href="/puckart/searchtype.action?pageNow=5&node=' + node + '">5</a></li>' +
                '<li><span>...</span></li>' +
                '<li class="next"><a href="/puckart/searchtype.action?pageNow=3&node=' + node + '">»</a></li>';
        } else if (pagenow == 3) {
            Pagination.innerHTML = '<li><a href="/puckart/searchtype.action?pageNow=2&node=' + node + '">«</a></li>' +
                '<li><a href="/puckart/searchtype.action?pageNow=1&node=' + node + '">1</a></li>' +
                '<li><a href="/puckart/searchtype.action?pageNow=2&node=' + node + '">2</a></li>' +
                '<li class="curent"><a href="/puckart/searchtype.action?pageNow=3&node=' + node + '">3</a></li>' +
                '<li><a href="/puckart/searchtype.action?pageNow=4&node=' + node + '">4</a></li>' +
                '<li><a href="/puckart/searchtype.action?pageNow=5&node=' + node + '">5</a></li>' +
                '<li><span>...</span></li>' +
                '<li class="next"><a href="/puckart/searchtype.action?pageNow=4&node=' + node + '">»</a></li>';
        } else if (pagenow >= 4) {
            Pagination.innerHTML = '<li><a href="/puckart/searchtype.action?pageNow=' + (pagenow - 1) + '&node=' + node + '">«</a></li>' +
                '<li><span>...</span></li>' +
                '<li><a href="/puckart/searchtype.action?pageNow=' + (pagenow - 2) + '&node=' + node + '">' + (pagenow - 2) + '</a></li>' +
                '<li><a href="/puckart/searchtype.action?pageNow=' + (pagenow - 1) + '&node=' + node + '">' + (pagenow - 1) + '</a></li>' +
                '<li class="curent"><a href="/puckart/searchtype.action?pageNow=' + pagenow + '&node=' + node + '">' + (pagenow) + '</a></li>' +
                '<li><a href="/puckart/searchtype.action?pageNow=' + (pagenow + 1) + '&node=' + node + '">' + (pagenow + 1) + '</a></li>' +
                '<li><a href="/puckart/searchtype.action?pageNow=' + (pagenow + 2) + '&node=' + node + '">' + (pagenow + 2) + '</a></li>' +
                '<li><span>...</span></li>' +
                '<li class="next"><a href="/puckart/searchtype.action?pageNow=' + (pagenow + 1) + '&node=' + node + '">»</a></li>';
        } else {
            Pagination.innerHTML =
                '<li class="curent"><a href="/puckart/searchtype.action?pageNow=1&node=' + node + '">1</a></li>' +
                '<li><a href="/puckart/searchtype.action?pageNow=2&node=' + node + '">2</a></li>' +
                '<li><a href="/puckart/searchtype.action?pageNow=3&node=' + node + '">3</a></li>' +
                '<li><a href="/puckart/searchtype.action?pageNow=4&node=' + node + '">4</a></li>' +
                '<li><a href="/puckart/searchtype.action?pageNow=5&node=' + node + '">5</a></li>' +
                '<li><span>...</span></li>' +
                '<li class="next"><a href="/puckart/searchtype.action?pageNow=2&node=' + node + '">»</a></li>';
        }
    }

});
