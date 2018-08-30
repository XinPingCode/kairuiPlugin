# kairuiPlugin
kairuiPlugin

isInstall() {
    cordova.plugins.kairuiPlugin.isInstall(1, result => {
      console.log("re:", result);
    }, error => console.log("err:", error));
  }
  
  startService() {
    cordova.plugins.kairuiPlugin.startService(this.tel, this.password, result => {
      console.log("re:", result);
    }, error => console.log("err:", error));
  }
  
  startRTVideo() {
    cordova.plugins.kairuiPlugin.startRTVideo(this.tel, this.password, this.callNum, result => {
      console.log("re:", result);
    }, error => console.log("err:", error));
  }
  
  startRTVideoName() {
    cordova.plugins.kairuiPlugin.startRTVideoName(this.tel, this.password, this.callNum, this.callName, result => {
      console.log("re:", result);
    }, error => console.log("err:", error));
  }
  
  startLocation() {
    cordova.plugins.kairuiPlugin.startLocation(this.isLoc, result => {
      console.log("re:", result);
    }, error => console.log("err:", error));
  }
  
  exitApp() {
    cordova.plugins.kairuiPlugin.exitApp(1, result => {
      console.log("re:", result);
    }, error => console.log("err:", error));
  }
