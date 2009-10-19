Ext.onReady(function() {

	var mainPanel = new Ext.TabPanel({
		border: false,
		activeTab: 0,
		height: 1000
	});

	var navigationPanel = createNavigationTree(mainPanel);

	new Ext.Viewport({
		layout: 'border',
		items: [
			{ region: 'center', items: mainPanel }, 
			{ region: 'west', title: 'Expressions', split: true, width: 200, collapsible: true, margins: '0 0 0 5', items: navigationPanel },
			{ region: 'north', height: 32, contentEl: 'header' },
			{ region: 'south', title: 'Info', split: true, height: 100, collapsible: true },
		]
        });
	
});

function createNavigationTree(mainPanel) {
	var root = new Ext.tree.AsyncTreeNode({
		expanded: true,
		text: 'Queries',
		children: [
			{ text: 'Tweets', leaf: true },
			{ text: 'Stuff', leaf: true },
			{ text: 'Top words', leaf: true },
			{ text: 'Good stuff', leaf: true },
			{ text: 'Interesting things', leaf: true },
		]
	})
	return new Ext.tree.TreePanel({ useArrows:true, border:false, autoScroll:true, animate:true, containerScroll:true, rootVisible:true, frame:false, root: root,	listeners: {
		dblclick: function(node){ createQueryEditorWindow(mainPanel, node.text); }
	}});
}

function createQueryEditorWindow(mainPanel, queryName) {
	var id = 'query:' + queryName;
	if (!mainPanel.getItem(id)) {
		mainPanel.add({
			id: id,
			title: queryName,
			closable:true,
			layout:'form',
			frame:true,
			bodyStyle:'padding:5px 5px 0',
			height: 330,
			items: [
				new Ext.form.TextField({fieldLabel: 'Name', name: 'name', value: queryName, allowBlank:false}),
				new Ext.form.TextArea({fieldLabel: 'Expression', name: 'expression', allowBlank:false, height: 200, anchor:'98%'}),
				new Ext.form.Checkbox({fieldLabel: 'Pattern', name: 'isPattern'}),
			],
			buttons: [
				{ text: 'Save' },
				{ text: 'Cancel' },
			]
		});
	}
	mainPanel.setActiveTab(id);
}
