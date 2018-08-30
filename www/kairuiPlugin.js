var exec = require('cordova/exec');

exports.startService = function (arg0, arg1, success, error){
    exec(success, error , 'kairuiPlugin', 'startService' ,[arg0,arg1]);
};
exports.startRTVideo = function (arg0, arg1,arg2,success, error){
    exec(success, error , 'kairuiPlugin', 'startRTVideo' ,[arg0,arg1,arg2]);
};
exports.startRTVideoName = function (arg0, arg1,arg2,arg3,success, error){
    exec(success, error , 'kairuiPlugin', 'startRTVideo' ,[arg0,arg1,arg2,arg3]);
};
exports.exitApp = function (arg0, success, error){
    exec(success, error , 'kairuiPlugin', 'exitApp' ,[arg0]);
};
exports.isInstall = function (arg0, success, error){
    exec(success, error , 'kairuiPlugin', 'isInstall' ,[arg0]);
};
exports.startLocation = function (arg0, success, error){
    exec(success, error , 'kairuiPlugin', 'startLocation' ,[arg0]);
};