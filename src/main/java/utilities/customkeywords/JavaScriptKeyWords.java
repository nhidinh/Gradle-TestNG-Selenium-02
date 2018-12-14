package utilities.customkeywords;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * User: Nhi Dinh
 * Date: 29/11/2018
 */
public class JavaScriptKeyWords {
    public void executeJS(String javascript, WebElement element, WebDriver driver){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript(javascript, element);
    }
    public static String getJsDndHelper() {
        return "var target = arguments[0]," +
                "offsetX = arguments[1]," +
                "offsetY = arguments[2]," +
                "document = target.ownerDocument || document," +
                "window = document.defaultView || window;" +
                "" +
                "var input = document.createElement('INPUT');" +
                "input.type = 'file';" +
                "input.style.display = 'none';" +
                "input.id = 'jsInput';"+
                "input.onchange = function () {" +
                "  target.scrollIntoView(true);" +
                "  var rect = target.getBoundingClientRect()," +
                "  x = rect.left + (offsetX || (rect.width >> 1))," +
                "  y = rect.top + (offsetY || (rect.height >> 1))," +
                "  dataTransfer = { files: this.files };" +
                "  ['dragenter', 'dragover', 'drop'].forEach(function (name) {" +
                "var evt = document.createEvent('MouseEvent');" +
                "evt.initMouseEvent(name, !0, !0, window, 0, 0, 0, x, y, !1, !1, !1, !1, 0, null);" +
                "evt.dataTransfer = dataTransfer;" +
                "target.dispatchEvent(evt);" +
                "  });" +
                "  setTimeout(function () { document.body.removeChild(input); }, 25);" +
                "};" +
                "document.body.appendChild(input);"+
                "return input.id";
    }

    public static String getJsDndHelper2(){
        return "var tgt=arguments[0],e=document.createElement('input');e.type='" +
                "file';e.addEventListener('change',function(event){var dataTrans" +
                "fer={dropEffect:'',effectAllowed:'all',files:e.files,items:{},t" +
                "ypes:[],setData:function(format,data){},getData:function(format" +
                "){}};var emit=function(event,target){var evt=document.createEve" +
                "nt('Event');evt.initEvent(event,true,false);evt.dataTransfer=da" +
                "taTransfer;target.dispatchEvent(evt);};emit('dragenter',tgt);em" +
                "it('dragover',tgt);emit('drop',tgt);document.body.removeChild(e" +
                ");},false);document.body.appendChild(e);return e;";
    }
}
