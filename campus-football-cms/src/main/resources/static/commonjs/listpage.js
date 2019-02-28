function arrayPage(pages,total,page) {
		var totalPage = pages;//总页数
		var totalRecords = total;//总记录数（数据条数）
		var pageNo = page;
		if(!pageNo){
			pageNo = 1;
		}
		//生成分页
		jpager.generPageHtml({
			pno : pageNo,
			//总页码
			total : totalPage,
			//总数据条数
			totalRecords : totalRecords,
            mode: 'click',
            click: function (n) {
                search(n);
                this.selectPage(n);
                return false;
            }
        }, true);

}
//ajax翻页
function search(n) {
    this.searchPage(n);
}


