<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>
        <#include "/edit/head.ftl"  />
    </head>

    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="/img/spinner.gif" alt="Loading..." />
        </div>
        <div class="nav">
            <span class="menuButton"><a class="home" href="/edit/home">Home</a></span>
            <span class="menuButton"><a href="/edit/logs/list" class="list">Ricerca</a></span>
        </div>
        <div class="body">
            <h1>Edit Log</h1>
            
            <form action="/edit/logs/doUpdate" method="post" >
                <input type="hidden" name="id" value="${log.id}" id="id" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="id">Id</label>
                                </td>
                                <td valign="top" class="value ">
                                    ${log.id}
                                </td>
                            </tr>                   
                             <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="user_id">user_id</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" name="user_id" value="${log.user_id}" id="user_id" size="20" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="ipFromRequest">ipFromRequest</label>
                                </td>

                                <td valign="top" class="value ">
                                    <input type="text" name="ipFromRequest" value="${log.ipFromRequest}" id="ipFromRequest" size="45"  maxlength="45"  />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="code">Codice</label>
                                </td>

                                <td valign="top" class="value ">
                                    <input type="text" name="code" value="${log.code}" id="code" size="45"  maxlength="45"  />
                                </td>
                            </tr>
                       
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="insertTime">insertTime</label>
                                </td>

                                <td valign="top" class="value ">
                                    <input type="text" name="insertTime" value="${log.insertTime}" id="insertTime" size="45"  maxlength="45" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="result">result</label>
                                </td>

                                <td valign="top" class="value ">
                                    <input type="text" id="result"  name="result" value="${log.result}" size="45"  maxlength="45" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="message">message</label>
                                </td>

                                <td valign="top" class="value ">
                                    <input type="text" id="message"  name="message" value="${log.message}" size="120"  maxlength="120" />
                                </td>
                            </tr>
                            
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
            			<span class="button"><input type="hidden" value="Update" class="edit" /></span>
	            </form>
	            
	            <form action="/edit/logs/delete/${log.id}" method="GET" >
	                    <span class="button"><input type="hidden" value="Delete" class="delete" onclick="return confirm('Are you sure?');" /></span>
	             </form>

             </div>

		</div>
    </body>
</html>