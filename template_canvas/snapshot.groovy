
def prop1 = new Properties()
prop1.load(new FileInputStream('local.properties'))
def config = new ConfigSlurper().parse(prop1)

//classLoader = Thread.currentThread().contextClassLoader
classLoader = this.class.classLoader.rootLoader
classLoader.addURL(new URL("file:${config.sdk.dir}/tools/lib/monkeyrunner.jar"))

/*
import com.android.monkeyrunner.adb.AdbBackend;
import com.android.monkeyrunner.core.IMonkeyDevice;
import com.android.monkeyrunner.core.TouchPressType;
*/

//adb = new com.android.monkeyrunner.adb.AdbBackend();
adb = Class.forName("com.android.monkeyrunner.adb.AdbBackend").newInstance()
device = adb.waitForConnection();

// Actions should go here
//device.touch(200, 200,TouchPressType.DOWN);

result = device.takeSnapshot()
result.writeToFile('1.png','png')

device.dispose();
