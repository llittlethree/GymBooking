/**
 * 验证报名信息的文件
 * 使用方法举例：
 * var adminName = check_LoginUsername();
 */
//var app = getApp();
var Message;//返回值 true为通过验证，否则就返回提示
// var RegExp=new RegExp();
/*
*验证姓名
姓名不能为数字，不能为英文字符，2~6中文字符，不能为空，不能为空格
 */

function checkReg_username(username) {
    var checkstring = new RegExp('[^a-z0-9_-]{2,6}', 'g');
    if (checkstring.test(username)) {
        if (username.length == 0 || username === "" || username == null) {
            return Message = "请输入姓名";
        } else {
            return Message = 'true';
        }
    } else {
        return Message = "姓名格式不正确，请检查";
    }
}

/**
 * 验证手机号
 * 手机号长度不能超过11位，必须以1开始，长度为6-11，不能出现中文，字母，
 */
function checkReg_telphone(telphone) {
    if(telphone.length!=0){
        var checkstring = new RegExp('^1[3|4|5|7|8][0-9]{9}', 'g');
        if (checkstring.test(telphone)) {
            return Message = 'true';
        } else {
            return Message = "手机格式不正确，检查后重新输入";
        }
    }else{
        return Message = "请输入手机号";
    }
}

/**
 * 验证身份证号码
 * 长度必须为18位，不能出现中文，特殊字符   空格
 */
function checkReg_Idcard(Idcard) {

    var checkstring = new RegExp('[1-9]\\d{5}([1-3]\\d{3})(0[1-9]|11|12)(0[1-9]|1\\d|2\\d|3[0-1])\\d{3}[\\d|X|x]', 'g');

    //450721199612266390
    if (checkstring.test(Idcard)) {
        return Message = 'true';
    } else {
        return Message = "身份证号码不正确";
    }

    //return checkstring.test(Idcard);
}

/**
 * 验证账号
 * 长度必须为5-12位，不能为中文
 */
function check_LoginUsername(username){
	var checkstring = new RegExp('[0-9A-Za-z]{5,12}', 'g');
	if (checkstring.test(username)) {
        return Message = 'true';
    } else {
        return Message = "账号格式不正确，请检查";
    }
}
/**
 * 验证密码
 * 长度必须为6-12位，不能为中文
 */
function check_password(password) {
    var checkstring = new RegExp('[0-9A-Za-z]{6,12}', 'g');
    if (checkstring.test(password)) {
        return Message = 'true';
    } else {
        return Message = "密码格式不正确";
    }
}

/**验证密码和确认密码 */
function check_repassword(repassword, password) {
    var checkstring = new RegExp('[0-9A-Za-z]{6,12}', 'g');
    if (checkstring.test(repassword)) {
        if (repassword === password) {
            return Message = 'true';
        } else {
            return Message = "两次密码输入不正确";
        }
    } else {
        return Message = "密码格式不正确";
    }
}

/**
 * 验证密码,密码 必须存在数字，英文字符，特殊字符
 * (?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}
 * ([a-zA-Z]+[0-9]+[!@#$%^&*]+)|([a-zA-Z]+[!@#$%^&*]+[0-9]+)|([0-9]+[!@#$%^&*]+[a-zA-Z]+)|([0-9]+[a-zA-Z]+[!@#$%^&*]+)|([!@#$%^&*]+[a-zA-Z]+[0-9]+)|([!@#$%^&*]+[0-9]+[a-zA-Z]+){6,12}
*/
function checkPasswordSpecial(password){
    if(password.length!=0){
        var checkstring = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,12}', 'g');
        if (checkstring.test(password)) {
            //console.log(password);
            return Message = 'true';
        } else {
            //console.log(password);
            return Message = "密码必须由数字，英文字母和特殊字符 !@#$%^&* 组成";
        }
    }else{
        return Message = "请输入密码";
    }
}

/**验证密码和确认密码必须存在数字，英文字符，特殊字符!@#$%^&*
 */
function checkRepasswordSpecial(repassword, password) {
    if(repassword.length!=0){
        if(password.length!=0){
            var checkstring = new RegExp('([a-zA-Z]+[0-9]+[!@#$%^&*]+)|([a-zA-Z]+[!@#$%^&*]+[0-9]+)|([0-9]+[!@#$%^&*]+[a-zA-Z]+)|([0-9]+[a-zA-Z]+[!@#$%^&*]+)|([!@#$%^&*]+[a-zA-Z]+[0-9]+)|([!@#$%^&*]+[0-9]+[a-zA-Z]+){6,12}', 'g');
            if (checkstring.test(repassword)) {
                if (repassword === password) {
                    return Message = 'true';
                } else {
                    return Message = "两次密码输入不正确";
                }
            } else {
                return Message = "确认密码必须由数字，英文字母和特殊字符 !@#$%^&* 组成";
            }
        }else{
            return Message = "请输入密码";
        }
    }else{
        return Message = "请输入确认密码";
    }
}

//时间戳转换为日期格式
function changeDate(fmt) {
    var Y,M,D,h,m,s;
   // var nowDate = Date.parse(new Date()) / 1000;
    var date = new Date(fmt * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    return Y + M + D + h + m + s;
}



///*module.exports = {
//	check_LoginUsername: check_LoginUsername,
//    checkReg_username: checkReg_username,
//    checkReg_telphone: checkReg_telphone,
//    checkReg_Idcard: checkReg_Idcard,
//    check_password: check_password,
//    check_repassword: check_repassword,
//    changeDate: changeDate,
//    checkPasswordSpecial: checkPasswordSpecial,
//}*/