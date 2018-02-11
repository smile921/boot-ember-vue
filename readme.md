# SpringBoot Ember or Vue

##  gradle build 的部分说明
* 修改 gradle脚本 在eclipse ，需要执行 
  * `gradle cleanEclipse`
  * `gradle eclipse`
  * 然后在刷新工程特别是添加新的依赖后，不然该jar包的引入需要在下次eclipse重启后

# build dev 过程热部署 spring-boot-devtools
devtools模块，是为开发者服务的一个模块。主要的功能就是代码修改后一般在5秒之内就会自动重新加载至服务器，相当于restart成功。  