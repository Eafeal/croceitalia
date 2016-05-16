Ext.onReady(function(){
    Ext.QuickTips.init();

    var login = new Ext.FormPanel({ 
        labelWidth:80,
        frame:true, 
        title:'Login CarteWEB', 
        defaultType:'textfield',
		standardSubmit: true,

		items:[{ 
                fieldLabel:'Username', 
                name:'j_username', 
                id:'j_username',
                allowBlank:false,
                onBlur: function(){
	                Ext.get(this.id).dom.value = Ext.get(this.id).dom.value.toUpperCase();
				}  
            },{ 
                fieldLabel:'Password', 
                name:'j_password', 
                inputType:'password', 
                allowBlank:false
            }],
 
        buttons:[{ 
	                text:'Login',
	                formBind: true,	 
	                handler: function() {
						login.getForm().getEl().dom.action = 'j_security_check';
		        		login.getForm().getEl().dom.method = 'POST';
	                	login.getForm().submit();
	                }
	             },{
			        text:'Change Password',
			        formBind: true,
			        handler: function(){
				        alert("cambia password");
			        }
		        }
        ] 
    });
    
    var changePassword = new Ext.Panel({
	    contentEl:"changePasswordDiv"
    })
 
    var win = new Ext.Window({
        layout:'fit',
        width:300,
        height:150,
        closable: false,
        resizable: false,
        plain: true,
        border: false,
        items: [login, changePassword]
	});
	win.show();

});
