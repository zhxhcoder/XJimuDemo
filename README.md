# XJimuDemo


慢慢从零开始对公司项目进行组件化改造
https://www.jianshu.com/p/67ddf272a341

项目结构图
![](https://github.com/zhxhcoder/XJimuDemo/blob/master/screenshots/xjimu.png)


参考文章
https://www.jianshu.com/p/1b1d77f58e84

按照 https://github.com/mqzhangw/JIMU 开源组件开发

详细步骤：

1：创建一个project,用创建project时自带的app module作为host项目

2：创建一个名为baselib的Android Library,用来放置公共类、工具类、以及一些公共的依赖等。

3：创建一个名为corelib的Android Library,用来放置公共类、工具类、以及一些公共的依赖等。

4：创建一个名为componentservice的Android Library,用来为app、read、share互相调用提供服务支持

5：创建read项目和share项目

6：让componentservice依赖baselib 和corelib，app、share、read都依赖componentservice，此时运行一下app、share、read，都能运行

7：配置工程下的build.gradle文件，注意，刚开始时先完全按照demo中的配置，等项目组件化做完之后，再根据自己的需求对不需要的引用进行删减；build.gradle文件配置完成后，com.dd.comgradle插件就已经引入到的项目中。

8：修改read、share、app的build.gradle中的apply plugin: 'com.android.application'为apply plugin: 'com.dd.comgradle'，修改完后同步一下项目，会提示设置isRunAlone。

9：将工程下的gradle.properties文件复制到share和read项目的根目录下，并在share和read项目的根目录下的gradle.properties文件的最后一行加入isRunAlone=true

10：将工程下的gradle.properties文件复制到app项目的根目录下，并进行如下设置：isRunAlone=true debugComponent=read,sharecompileComponent=read,share设置完成后同步一下项目，会报错：Error:(1, 0) Could not get unknown property 'mainmodulename' for root project 'MyZuJianHuaDemo' of type org.gradle.api.Project.解决办法：在工程目录下的gradle.properties文件中添加mainmodulename=app，报错解决


（1）在read项目的main文件夹下创建runalone文件夹，在runalone文件夹下创建java文件夹，在java文件夹中创建runalone文件夹，在runalone文件夹下创建application文件夹，在application文件夹下创建ReadApplication类，并继承BaseApplication

（2）复制read项目的AndroidManifest.xml文件到runalone文件夹下，然后将runalone文件夹外的AndroidManifest.xml文件去掉intent-filter标签，即去掉其作为一个完整APP的入口，使read项目的入口变成runalone文件夹下的AndroidManifest.xml文件。

（3）对share项目进行同样的操作同步一下项目，不会报错

11：修改share项目和read项目的build.gradle文件

(1)修改share项目的build.gradle文件，在defaultConfig标签的最后添加： //此处的"share"是跳转URI中的host名称，每个组件需要设置不同的host。 javaCompileOptions { annotationProcessorOptions { arguments = [host: "share"] } }

(2)在android标签的最后添加：resourcePrefix "share_"

(3)在build.gradle文件的最后添加combuild { applicationName = 'application.ShareAppliaction' isRegisterCompoAuto = true}repositories { mavenCentral()}注意：application.ShareAppliaction是runalone文件夹下ShareAppliaction的路径

（4）在依赖中加入：annotationProcessor deps.jimu.router_anno_compiler，用于进行UI跳转

（5）对read项目进行同样的操作

12：在componentservice的build.gradle中加入：resourcePrefix common_"

13：修改app项目的build.gradle文件

（1）在defaultConfig标签的最后添加：javaCompileOptions { annotationProcessorOptions {arguments = [host: "app"] }} vectorDrawables.useSupportLibrary = true

（2）在android标签的最后添加： lintOptions {checkReleaseBuilds false// Or, if you prefer, you can continue to check for errors in release builds,// but continue the build even when errors are found:abortOnError false }

（3）在最后添加：combuild { applicationName = 'com.zhxh.xjimudemo.AppApplication' isRegisterCompoAuto = true}

14：设置applike文件夹

（1）在baselib中的build.gradle文件中添加依赖：compile deps.jimu.componentLib

（2）在share项目中创建applike文件夹，在该文件夹下创建ShareAppLike类实现IApplicationLike接口，并重写onCreate和onStop方法，

（3）在read项目中进行同样的操作

（4）在app项目中的AppApplication类中添加demo中对应的代码

15：在BaseActivity中的onCreate()方法中加入以下代码：//依赖注入：如果想使用自动装载功能，需要在Activity的onCreate中调用方法AutowiredService.Factory.getSingletonImpl().autowire(this);

16：去掉read、share中的build.gradle中的applicationId，保留app中的applicationId15:在app项目的清单文件中的application标签中加上name字段，否则注册不了read和shareread和share的runalone文件夹中的清单文件也要在application标签中加上name字段

17：跳转到哪个Activity，就在runalone文件夹外的清单文件中对应的Activity中添加一下代码

18：添加卸载组件

//注意此处的参数为：该组件java文件夹下该类的全路径

Router.registerComponent("com.zhxh.share.applike.ShareAppLike");

Router.unregisterComponent("com.zhxh.share.applike.ShareAppLike");

19：将read组件作为host组件来调用share组件的方法

（1）在read组件的ReadApplication的onCreate()方法中注册share组件

Router.registerComponent("com.zhxh.share.applike.ShareAppLike");

（2）在read组件的gradle.properties文件中加入以下代码：

debugComponent=share

compileComponent=share

不加的话注册share组件不成功；注册share组件成功的标志是ShareAppLike类的onCreate()方法执行了

（3）此处要注意ReadApplication类的onCreate()方法有无执行，此处要注意：runalone下的java文件夹下不能直接创建

application文件夹，需要在这二者之间再创建一个文件夹进行分隔

20：注意：runalone文件夹内外的两个清单文件中都要声明activity
