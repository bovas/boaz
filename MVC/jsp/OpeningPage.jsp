<%@page import="java.io.*,java.util.*"%>

<html>
<head>
	<script type='text/javascript' language='javascript' src='jsp/menuConfig.js'></script>
	<script language='javascript1.2'>	
	<%
			String menuIds = "";
			if (request.getAttribute("mainMenus") != null) {
				ArrayList mainMenusList = (ArrayList) request.getAttribute("mainMenus");				
				HashMap menu = null;
				StringBuilder grpname = new StringBuilder();
				StringBuilder menuname = new StringBuilder();
				StringBuilder menuurl = new StringBuilder();
				StringBuilder isactive = new StringBuilder();
				StringBuilder loadloc = new StringBuilder();
				int preGroup = -1;
				out.println(" var MenuLinks    = new Array();");
				for (int i = 0, j = 0; i < mainMenusList.size(); i++) {
					menu = (HashMap) mainMenusList.get(i);					
					menuIds += menu.get("menuid") + ",";
					if (Integer.parseInt(menu.get("groupid").toString()) != preGroup && j != 0) {
						menuname.append(");");
						menuurl.append(");");
						isactive.append(");");
						loadloc.append(");");
						out.println(grpname.toString() + menuname.toString() + menuurl.toString() + isactive.toString() + loadloc.toString());
						//GlaceOutLoggingStream.console(grpname.toString()+menuname.toString()+menuurl.toString()+isactive.toString()+loadloc.toString());
						grpname = new StringBuilder();
						menuname = new StringBuilder();
						menuurl = new StringBuilder();
						isactive = new StringBuilder();
						loadloc = new StringBuilder();
					}
					grpname.append(((Integer.parseInt(menu.get("groupid").toString()) != preGroup) ? " MenuLinks[" + j++
							+ "] = \"" + menu.get("groupname").toString()+ "\";" : ""));
					menuname.append(((Integer.parseInt(menu.get("groupid").toString()) != preGroup) ? " MenuLinks[" + j++
							+ "] = new Array(" : ",")
							+ "\"" + (menu.get("menuname")).toString() + "\"");
					menuurl.append(((Integer.parseInt(menu.get("groupid").toString()) != preGroup) ? " MenuLinks[" + j++
							+ "] = new Array(" : ",")
							+ "\"" + (menu.get("menuurl")).toString() + "\"");
					isactive.append(((Integer.parseInt(menu.get("groupid").toString()) != preGroup) ? " MenuLinks[" + j++
							+ "] = new Array(" : ",")+ "true");
					loadloc.append(((Integer.parseInt(menu.get("groupid").toString()) != preGroup) ? " MenuLinks[" + j++
							+ "] = new Array(" : ",")+ "\"" + (menu.get("loadloc")).toString() + "\"");
					preGroup = Integer.parseInt(menu.get("groupid").toString());					
					if (i == mainMenusList.size() - 1) {
						menuname.append(");");
						menuurl.append(");");
						isactive.append(");");
						loadloc.append(");");
						out.println(grpname.toString() + menuname.toString()+ menuurl.toString() + isactive.toString()+ loadloc.toString());						
						grpname = new StringBuilder();
						menuname = new StringBuilder();
						menuurl = new StringBuilder();
						isactive = new StringBuilder();
						loadloc = new StringBuilder();
					}
				}				
				grpname = null;
				menuname = null;
				menuurl = null;
				isactive = null;
				loadloc = null;
			} else
				out.println(" ");
		%>
		var mIdsObj = new Array();
		 mIdsObj  = [<%=menuIds %>];
		</script>
</head>
<body onload="">
<div class="ToolsRow TopRow">
	<table class="DetailMenu" cellpadding="0" cellspacing="0">
		<tbody>
			<tr valign="top">
				<td title="EMR"
					style="cursor: pointer; border-bottom: 5px solid rgb(255, 255, 255); background-color: blue ;"
					id="MenuArea0"><div id="Home0" class="MainMenuLink">EMR</div></td>
				<td title="Billing"
					style="cursor: pointer; border-bottom: 5px solid rgb(255, 255, 255); background-color: blue ;"
					id="MenuArea1"><div id="Home1" class="MainMenuLink">Billing</div></td>
				<td title="Lists"
					style="cursor: pointer; border-bottom: 5px solid rgb(255, 255, 255); background-color: blue ;"
					id="MenuArea2"><div id="Home2" class="MainMenuLink">Lists</div></td>
				<td title="Reports"
					style="cursor: pointer; border-bottom: 5px solid rgb(255, 255, 255); background-color: blue ;"
					id="MenuArea3"><div id="Home3" class="MainMenuLink">Reports</div></td>
				<td title="Utilities"
					style="cursor: pointer; border-bottom: 5px solid rgb(255, 255, 255); background-color: blue ;"
					id="MenuArea4"><div id="Home4" class="MainMenuLink">Utilities</div></td>
				<td title="Configure"
					style="cursor: pointer; border-bottom: 5px solid rgb(255, 255, 255); background-color: blue ;"
					id="MenuArea5"><div id="Home5" class="MainMenuLink">Configure</div></td>
				<td title="Help"
					style="cursor: pointer; border-bottom: 5px solid rgb(255, 255, 255); background-color: blue;"
					id="MenuArea6"><div id="Home6" class="MainMenuLink">Help</div></td>
			</tr>
		</tbody>
	</table>
</div>
</body>
<script type="text/javascript">
initTransMenuScript();
</script>
</html>