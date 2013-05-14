var scMenuArray = null;
var menuarray=new Array();
var len=0;
var path=""; 

function addPopupArray(popup){
	if(popup != null){
		var i = popArray.length;
		popArray[i] = popup;
	}
}
function closePopups(){
	try{
		for(var i = 0; i < popArray.length; i++){
			if(popArray[i] != null){
				popArray[i].close();
			}
		}
	}
	catch(exception){
		//alert("exception in closing popups!");
	}	
}



function createCookie(name,value,days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}
	else var expires = "";
	document.cookie = name+"="+value+expires+"; path=/";
}

function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

function eraseCookie(name) {
	createCookie(name,"",-1);
}

//==================================================================================================
// Configuration properties
//==================================================================================================
TransMenu.dingbatOn = "";            // path to the active sub menu dingbat
TransMenu.dingbatOff = "";          // path to the inactive sub menu dingbat
TransMenu.dingbatSize = 14;                            // size of the dingbat (square shape assumed)
TransMenu.menuPadding = 5;                             // padding between menu border and items grid
TransMenu.itemPadding = 3;                             // additional padding around each item
TransMenu.shadowSize = 2;                              // size of shadow under menu
TransMenu.shadowOffset = 3;                            // distance shadow should be offset from leading edge
TransMenu.shadowColor = "";                        // color of shadow (transparency is set in CSS)
TransMenu.shadowPng = "";               // a PNG graphic to serve as the shadow for mac IE5
TransMenu.backgroundColor = "white";                   // color of the background (transparency set in CSS)
TransMenu.backgroundPng = "";          // a PNG graphic to server as the background for mac IE5
TransMenu.hideDelay = 400;                            // number of milliseconds to wait before hiding a menu
TransMenu.slideTime = 50;                             // number of milliseconds it takes to open and close a menu


//==================================================================================================
//TransMenuItem (internal)
//represents an item in a dropdown
//==================================================================================================
//sText        : The item display text
//sUrl         : URL to load when the item is clicked
//oParent      : Menu this item is a part of
//==================================================================================================
function TransMenuItem(sText, sUrl, oParent, mIdx) {
     this.toString = toString;
     this.text = sText;
     this.url = sUrl;
     this.parentMenu = oParent;
     if(!scMenuArray){
     	scMenuArray = new Array();
     }
     var sKey = sText.replace("'", "").replace(" ","");
     scMenuArray[sKey] = sUrl;
     function toString(bDingbat) {
             var sDingbat = bDingbat ? TransMenu.dingbatOff : TransMenu.spacerGif;
             var iEdgePadding = TransMenu.itemPadding + TransMenu.menuPadding;
             var sPaddingLeft = "padding:" + TransMenu.itemPadding + "px; padding-left:" + (11+iEdgePadding) + "px;"
             var sPaddingRight = "padding:" + TransMenu.itemPadding + "px; padding-right:" + iEdgePadding + "px;"
             return '<tr id="_gl'+mIdx+'" class="item"><td onmouseover="trans_callListenerFunction()" style="' + sPaddingLeft + '" title="'+sText+'">' + 
                     sText +'</td></tr>';
     }
}

//==================================================================================================
// Internal use properties
//==================================================================================================
TransMenu.reference = {topLeft:1,topRight:2,bottomLeft:3,bottomRight:4};
TransMenu.direction = {down:1,right:2};
TransMenu.registry = [];
TransMenu._maxZ = 100;

function TransMenu(oActuator, iDirection, iLeft, iTop, iReferencePoint, parentMenuSet) {
	 //alert('TransMenu');
	 this.addItem = addItem;
     this.addMenu = addMenu;
     this.toString = toString;
     this.initialize = initialize;
     this.isOpen = false;
     this.show = show;
     this.hide = hide;
     this.items = [];

     // events
     this.onactivate = new Function();       // when the menu starts to slide open
     this.ondeactivate = new Function();     // when the menu finishes sliding closed
     this.onmouseover = new Function();      // when the menu has been moused over
     this.onqueue = new Function();          // hack .. when the menu sets a timer to be closed a little while in the future
     this.ondequeue = new Function();

     // initialization
     this.index = TransMenu.registry.length;
     TransMenu.registry[this.index] = this;

     var id = "TransMenu" + this.index;
     var contentHeight = null;
     var contentWidth = null;
     var childMenuSet = null;
     var animating = false;
     var childMenus = [];
     var slideAccel = -1;
     var elmCache = null;
     var ready = false;
     var _this = this;
     var a = null;

     var pos = iDirection == TransMenu.direction.down ? "top" : "left";
     var dim = null;
	   
     function addItem(sText, sUrl,mIdx) {
         var item = new TransMenuItem(sText, sUrl, this, mIdx);
         item._index = this.items.length;
			len=item._index;
         this.items[item._index] = item;
     }
     function addMenu(oMenuItem) {
         if (!oMenuItem.parentMenu == this) throw new Error("Cannot add a menu here");

         if (childMenuSet == null) childMenuSet = new TransMenuSet(TransMenu.direction.right, -5, 2, TransMenu.reference.topRight);

         var m = childMenuSet.addMenu(oMenuItem);

         childMenus[oMenuItem._index] = m;
         m.onmouseover = child_mouseover;
         m.ondeactivate = child_deactivate;
         m.onqueue = child_queue;
         m.ondequeue = child_dequeue;

         return m;
     }
     function initialize() {
         initCache();
         initEvents();
         initSize();
         ready = true;
     }
     function getBarColor(m_Indx) {
     	var barColors= new Array("#A6A6A6","#178CE0","#D9AA18","#F04247","#3DBA54","#B952C1","#999999");        	        	
     	switch (m_Indx) {
     		case "0":
     			return barColors[0];
     			break;
     		case "1":
     		case "7":
     			return barColors[1];
     			break;
     		case "2":
     			return barColors[2];
     			break;
     		case "3":
     		case "8":
     			return barColors[3];
     			break;
     		case "4":
     			return barColors[4];
     			break;
     		case "5":
     			return barColors[5];
     			break;
     		case "6":
     			return barColors[6];
     			break;
     		default:
     			//alert(m_Indx);        		
     	}
     }

     function show() {
     	var _innerTxt = this+'';
     	var _menuId = _innerTxt.charAt(18); /* getting Menu Indx */
     	var divObj = eval("document.getElementById('MenuArea"+_menuId+"')");
     	divObj.style.borderBottom = "5px solid #67AEEB"//+getBarColor(_menuId);
     	divObj.style.backgroundColor= "#67AEEB";
             if (ready) {
                     _this.isOpen = true;
                     animating = true;
                     setContainerPos();
                     elmCache["clip"].style.visibility = "visible";
                     elmCache["clip"].style.zIndex = TransMenu._maxZ++;
                     //dbg_dump("maxZ: " + TransMenu._maxZ);
                     slideStart();
                     _this.onactivate();
             }
     }

     function hide() {
     	var _innerTxt = this+'';
     	var _menuId = _innerTxt.charAt(18);        	
     	var divObj = eval("document.getElementById('MenuArea"+_menuId+"')");
     	divObj.style.borderBottom = '5px solid #FFF';    
     	divObj.style.backgroundColor= "transparent";
             if (ready) {
                     _this.isOpen = false;
                     animating = true;

                     for (var i = 0, item = null; item = elmCache.item[i]; i++) 
                             dehighlight(item);

                     if (childMenuSet) childMenuSet.hide();

                     slideStart();
                     _this.ondeactivate();
             }
     }

     function setContainerPos() {
             var sub = oActuator.constructor == TransMenuItem; 
             var act = sub ? oActuator.parentMenu.elmCache["item"][oActuator._index] : oActuator; 
             var el = act;
             
             var x = 0;
             var y = 0;

             
             var minX = 0;
             var maxX = (window.innerWidth ? window.innerWidth : document.body.clientWidth) - parseInt(elmCache["clip"].style.width);
             var minY = 0;
             var maxY = (window.innerHeight ? window.innerHeight : document.body.clientHeight) - parseInt(elmCache["clip"].style.height);

             // add up all offsets... subtract any scroll offset
             while (sub ? el.parentNode.className.indexOf("transMenu") == -1 : el.offsetParent) {
                     x += el.offsetLeft;
                     y += el.offsetTop;

                     if (el.scrollLeft) x -= el.scrollLeft;
                     if (el.scrollTop) y -= el.scrollTop;
                     
                     el = el.offsetParent;
             }

             if (oActuator.constructor == TransMenuItem) {
                     x += parseInt(el.parentNode.style.left);
                     y += parseInt(el.parentNode.style.top);
             }

             switch (iReferencePoint) {
                     case TransMenu.reference.topLeft:
                             break;
                     case TransMenu.reference.topRight:
                             x += act.offsetWidth;
                             break;
                     case TransMenu.reference.bottomLeft:
                             y += act.offsetHeight;
                             break;
                     case TransMenu.reference.bottomRight:
                             x += act.offsetWidth;
                             y += act.offsetHeight;
                             break;
             }

             x += iLeft;
             y += iTop;

             x = Math.max(Math.min(x, maxX), minX);
             y = Math.max(Math.min(y, maxY), minY);
             elmCache["clip"].style.left = (x-1) + "px";
             elmCache["clip"].style.top = (y+5) + "px";
     }

     function slideStart() {
             var x0 = parseInt(elmCache["content"].style[pos]);
             var x1 = _this.isOpen ? 0 : -dim;

             if (a != null) a.stop();
             a = new Accelimation(x0, x1, TransMenu.slideTime, slideAccel);

             a.onframe = slideFrame;
             a.onend = slideEnd;

             a.start();
     }

     function slideFrame(x) {
             elmCache["content"].style[pos] = x + "px";
     }

     function slideEnd() {
             if (!_this.isOpen) elmCache["clip"].style.visibility = "hidden";
             animating = false;
     }

     function initSize() {
             // everything is based off the size of the items table...
             var ow = elmCache["items"].offsetWidth;
             var oh = elmCache["items"].offsetHeight;
             var ua = navigator.userAgent.toLowerCase();

             // clipping container should be ow/oh + the size of the shadow
             elmCache["clip"].style.width = ow + TransMenu.shadowSize +  2 + "px";
             elmCache["clip"].style.height = oh + TransMenu.shadowSize + 2 + "px";

             // same with content...
             elmCache["content"].style.width = ow + TransMenu.shadowSize + "px";
             elmCache["content"].style.height = oh + TransMenu.shadowSize + "px";

             contentHeight = oh + TransMenu.shadowSize;
             contentWidth = ow + TransMenu.shadowSize;
             
             dim = iDirection == TransMenu.direction.down ? contentHeight : contentWidth;

             // set initially closed
             elmCache["content"].style[pos] = -dim - TransMenu.shadowSize + "px";
             elmCache["clip"].style.visibility = "hidden";

             // if *not* mac/ie 5
             if (ua.indexOf("mac") == -1 || ua.indexOf("gecko") > -1) {
                     // set background div to offset size
                     elmCache["background"].style.width = ow + "px";
                     elmCache["background"].style.height = oh + "px";
                     elmCache["background"].style.backgroundColor = TransMenu.backgroundColor;

                     // shadow left starts at offset left and is offsetHeight pixels high
                     elmCache["shadowRight"].style.left = ow + "px";
                     elmCache["shadowRight"].style.height = oh - (TransMenu.shadowOffset - TransMenu.shadowSize) + "px";
                     elmCache["shadowRight"].style.backgroundColor = TransMenu.shadowColor;

                     // shadow bottom starts at offset height and is offsetWidth - shadowOffset 
                     // pixels wide (we don't want the bottom and right shadows to overlap or we 
                     // get an extra bright bottom-right corner)
                     elmCache["shadowBottom"].style.top = oh + "px";
                     elmCache["shadowBottom"].style.width = ow - TransMenu.shadowOffset + "px";
                     elmCache["shadowBottom"].style.backgroundColor = TransMenu.shadowColor;
             }
             // mac ie is a little different because we use a PNG for the transparency
             else {
                     // set background div to offset size
                     elmCache["background"].firstChild.src = TransMenu.backgroundPng;
                     elmCache["background"].firstChild.width = ow;
                     elmCache["background"].firstChild.height = oh;

                     // shadow left starts at offset left and is offsetHeight pixels high
                     elmCache["shadowRight"].firstChild.src = TransMenu.shadowPng;
                     elmCache["shadowRight"].style.left = ow + "px";
                     elmCache["shadowRight"].firstChild.width = TransMenu.shadowSize;
                     elmCache["shadowRight"].firstChild.height = oh - (TransMenu.shadowOffset - TransMenu.shadowSize);

                     // shadow bottom starts at offset height and is offsetWidth - shadowOffset 
                     // pixels wide (we don't want the bottom and right shadows to overlap or we 
                     // get an extra bright bottom-right corner)
                     elmCache["shadowBottom"].firstChild.src = TransMenu.shadowPng;
                     elmCache["shadowBottom"].style.top = oh + "px";
                     elmCache["shadowBottom"].firstChild.height = TransMenu.shadowSize;
                     elmCache["shadowBottom"].firstChild.width = ow - TransMenu.shadowOffset;
             }
     }
     
     function initCache() {
             var menu = document.getElementById(id);
             var all = menu.all ? menu.all : menu.getElementsByTagName("*"); // IE/win doesn't support * syntax, but does have the document.all thing

             elmCache = {};
             elmCache["clip"] = menu;
             elmCache["item"] = [];
             
             for (var i = 0, elm = null; elm = all[i]; i++) {
                     switch (elm.className) {
                             case "items":
                             case "content":
                             case "background":
                             case "shadowRight":
                             case "shadowBottom":
                                     elmCache[elm.className] = elm;
                                     ////alert(elmCache+" : "+elm.className+" : "+elm+" => "+elmCache[elm.className]);
                                     break;
                             case "item":
                                     elm._index = elmCache["item"].length;
                                     elmCache["item"][elm._index] = elm;
                                     break;
                     }
             }

             // hack!
             _this.elmCache = elmCache;
     }

     function initEvents() {
             // hook item mouseover
             for (var i = 0, item = null; item = elmCache.item[i]; i++) {
                     item.onmouseover = item_mouseover;
                     item.onmouseout = item_mouseout;
                     item.onclick = item_click;
             }

             // hook actuation
             if (typeof oActuator.tagName != "undefined") {
                     oActuator.onmouseover = actuator_mouseover;
                     oActuator.onmouseout = actuator_mouseout;
             }

             // hook menu mouseover
             elmCache["content"].onmouseover = content_mouseover;
             elmCache["content"].onmouseout = content_mouseout;
     }

     function highlight(oRow) {
             oRow.className = "item hover";
             if (childMenus[oRow._index]) 
                     oRow.lastChild.firstChild.src = TransMenu.dingbatOn;
     }

     function dehighlight(oRow) {
             oRow.className = "item";
             if (childMenus[oRow._index]) 
                     oRow.lastChild.firstChild.src = TransMenu.dingbatOff;
     }

     function item_mouseover() {
                   if (!animating) {
                     highlight(this);

                     if (childMenus[this._index]) 
                             childMenuSet.showMenu(childMenus[this._index]);
                     else if (childMenuSet) childMenuSet.hide();
             }
     }

     function item_mouseout() {
             if (!animating) {
                     if (childMenus[this._index])
                             childMenuSet.hideMenu(childMenus[this._index]);
                     else    // otherwise child_deactivate will do this
                             dehighlight(this);
             }
     }

     function item_click() {
     
             if (!animating) {
                     if (_this.items[this._index].url) 
                             location.href = _this.items[this._index].url;
             }
             parentMenuSet.hideMenu(_this);
             animating=true;
     }

     function actuator_mouseover() {
             parentMenuSet.showMenu(_this);
     }

     function actuator_mouseout() {
             parentMenuSet.hideMenu(_this);
     }

     function content_mouseover() {
                if (!animating) {
                     parentMenuSet.showMenu(_this);
                     _this.onmouseover();
             }
     }

     function content_mouseout() {
                 if (!animating) {
                     parentMenuSet.hideMenu(_this);
             }
     }

     function child_mouseover() {
             if (!animating) {
                     parentMenuSet.showMenu(_this);
             }
     }

     function child_deactivate() {
             for (var i = 0; i < childMenus.length; i++) {
                     if (childMenus[i] == this) {
                             dehighlight(elmCache["item"][i]);
                             break;
                     }
             }
     }

     function child_queue() {
             parentMenuSet.hideMenu(_this);
     }

 function child_dequeue() {
     parentMenuSet.showMenu(_this);
 }
var src="src='../includes/Blank.html'";
     function toString() {
             var aHtml = [];
             if(!document.all)
             src="";
             var sClassName = "transMenu" + (oActuator.constructor != TransMenuItem ? " top" : "");

             for (var i = 0, item = null; item = this.items[i]; i++) {
                     aHtml[i] = item.toString(childMenus[i]);
             }
             return '<div id="' + id + '" class="' + sClassName + '">' + 
                     '<div class="content"><table class="items" cellpadding="0" cellspacing="0" border="0">' + 
                    // '<tr><td ></td></tr>' + 
                     aHtml.join('') + 
                     '<tr><td colspan="2"></td></tr></table>' + 
                     '<div class="shadowBottom"> </div>' + 
                     '<div class="shadowRight"></div>' + 
         '<div class="background"></div>' + 
               '</div><iframe id="iframe'+id+'" '+src+' style="FILTER: chroma (color=FFFFFF)" width=170px frameborder=0  scrolling=no height=300px></iframe></div>';
     }
}


//==================================================================================================
//TransMenuSet
//==================================================================================================
//iDirection           : The direction to slide out. One of TransMenu.direction.
//iLeft                : Left pixel offset of menus from actuator
//iTop                 : Top pixel offset of menus from actuator
//iReferencePoint      : Corner of actuator to measure from. One of TransMenu.referencePoint.
//==================================================================================================
TransMenuSet.registry = [];
function TransMenuSet(iDirection, iLeft, iTop, iReferencePoint){
	//alert('MenuSet');
	// public methods
    this.addMenu = addMenu;
    this.showMenu = showMenu;
    this.hideMenu = hideMenu;
    this.hide = hide;
    this.hideCurrent = hideCurrent;
    
    // initialization
    var menus = [];
    var _this = this;
    var current = null;
    
    this.index = TransMenuSet.registry.length;
    TransMenuSet.registry[this.index] = this;    
    // method implimentations...
    function addMenu(oActuator) {
    		//alert('addMenu');
            var m = new TransMenu(oActuator, iDirection, iLeft, iTop, iReferencePoint, this);            
            menus[menus.length] = m;            
            return m;
    }
    function showMenu(oMenu) {
    	//alert('showMenu');
        if (oMenu != current) {
                // close currently open menu
                if (current != null) hide(current);        

                // set current menu to this one
                current = oMenu;

                // if this menu is closed, open it
                oMenu.show();
        }
        else {
                // hide pending calls to close this menu
                cancelHide(oMenu);
        }
    }
    function hideMenu(oMenu) {
    	//alert('hidemenu');
        //dbg_dump("hideMenu a " + oMenu.index);
      if (current == oMenu && oMenu.isOpen) {
                //dbg_dump("hideMenu b " + oMenu.index);
              if (!oMenu.hideTimer) scheduleHide(oMenu);
      }
    }
    function hide(oMenu) {   
    	//alert('hide');
        if (!oMenu && current) oMenu = current;

       if (oMenu && current == oMenu && oMenu.isOpen) {
     //  //alert('hiding2');
       
               hideCurrent();
       }
    }
    function hideCurrent() {
    	//alert('hideCurrent');
        if (null != current) {
          cancelHide(current);
          current.hideTimer = null;
          current.hide();
          current = null;
        }
     }
    function scheduleHide(oMenu) {
        //dbg_dump("scheduleHide " + oMenu.index);
    	//alert('scheduleHide');
      oMenu.onqueue();
      oMenu.hideTimer = window.setTimeout("TransMenuSet.registry[" + _this.index + "].hide(TransMenu.registry[" + oMenu.index + "])", TransMenu.hideDelay);
	}
	
	function cancelHide(oMenu) {
			//alert('cancelHide');
	      //dbg_dump("cancelHide " + oMenu.index);
	      if (oMenu.hideTimer) {
	  oMenu.ondequeue();
	              window.clearTimeout(oMenu.hideTimer);
	              oMenu.hideTimer = null;
	      }
	}
}

function initTransMenuScript(){	
	if(TransMenu.isSupported()){		
		var ms = new TransMenuSet(TransMenu.direction.down, 1, 0, TransMenu.reference.bottomLeft);
		////alert("Total Menu Length : "+MenuLinks.length+" : \n"+MenuLinks);
		var mIdx = 0 ;
		   for(i=0;i<MenuLinks.length/5;i++){			  
			   menuarray[i] = ms.addMenu(document.getElementById("Home"+i));
			   for(j=0;j < MenuLinks[i*5+1].length; j++){						   
				   if(MenuLinks[i*5+3][j])					   
					    menuarray[i].addItem(MenuLinks[i*5+1][j],"javascript:gotoHeaderPage(\"" + path+ MenuLinks[i*5+2][j] + "\",\""+MenuLinks[i*5+4][j]+"\",\""+escape(MenuLinks[i*5+1][j])+"\");",mIdsObj[mIdx] );
					    ////alert(MenuLinks[i*5+1][j]+" : "+);
					    mIdx++;
			   }
		   }
		   TransMenu.renderAll();
	}		
}

TransMenu.isSupported = function() {
    var ua = navigator.userAgent.toLowerCase(); 	//user agent of browser
    var pf = navigator.platform.toLowerCase();		//linux 
    var an = navigator.appName;						//netscape
    var r = false;
    
    if (ua.indexOf("gecko") > -1 && navigator.productSub >= 20020605) r = true; // gecko >= moz 1.0
    else if (an == "Microsoft Internet Explorer") {
            if (document.getElementById) { // ie5.1+ mac,win
                    if (pf.indexOf("mac") == 0) {
          r = /msie (\d(.\d*)?)/.test(ua) && Number(RegExp.$1) >= 5.1;
        }
        else r = true;
            }
    }

    return r;
}
TransMenu.initialize = function() {	
    for (var i = 0, menu = null; menu = this.registry[i]; i++) {        		
            menu.initialize();
    }
}

//call this in document body to write out menu html
TransMenu.renderAll = function() {
    var aMenuHtml = [];
    for (var i = 0, menu = null; menu = this.registry[i]; i++) {
            aMenuHtml[i] = menu.toString();
    }    
    document.write(aMenuHtml.join(""));
}


var trans_MENU_MS_OVER_LISTENER;
function trans_callListenerFunction(){
	if(trans_MENU_MS_OVER_LISTENER && (typeof trans_MENU_MS_OVER_LISTENER=='function')){
		trans_MENU_MS_OVER_LISTENER();
	}
}
var setMenuMouseListener=function(mousover){
	trans_MENU_MS_OVER_LISTENER=mousover;
}
function initinit() {
  if (TransMenu.isSupported()) {
      TransMenu.initialize();
  }
}

