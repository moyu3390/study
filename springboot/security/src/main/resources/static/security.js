/**
 * 查询存款
 */
function getCurrentMoney() {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', '/bank/money', true);
    xhr.onreadystatechange = function (e) {
        if (this.readyState == 4 && this.status == 200) {
            let moneyView = document.getElementById("money");
            moneyView.value = this.responseText;
        }

    }
    xhr.send();
}

/**
 * 扣款
 */
function postMoney() {
    const xhr = new XMLHttpRequest();
    let csrfToken = getCookie("XSRF-TOKEN");
    xhr.open('POST', '/bank/money', true);
    xhr.setRequestHeader("X-XSRF-TOKEN", csrfToken);
    xhr.onreadystatechange = function (e) {
        if (this.readyState == 4 && this.status == 200) {
            let moneyView = document.getElementById("money");
            moneyView.value = this.responseText;
        }

    }
    xhr.send();
}

/**
 * 获取 cookie 值
 * @param c_name
 * @returns {*}
 */
function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=")
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1
            c_end = document.cookie.indexOf(";", c_start)
            if (c_end == -1) c_end = document.cookie.length
            return unescape(document.cookie.substring(c_start, c_end))
        }
    }
    return ""
}