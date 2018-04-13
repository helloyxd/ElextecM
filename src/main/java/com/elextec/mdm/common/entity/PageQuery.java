package com.elextec.mdm.common.entity;

public class PageQuery{

	private final int PAGE_SIZE_DEFAULT = 15;
    private int beginIndex;
    private int endIndex;
    private int allCount;
    private int currentPage;
    private int pageRowSize;
    private int allPageCount;
    private String order; 
    private String tableName;
    
    public PageQuery(){
    }
    
    public void calcutePage(PageQuery page){
        if(page.getCurrentPage() < 1)
        	page.setCurrentPage(1);
        if(page.getPageRowSize() < 1)
        	page.setPageRowSize(PAGE_SIZE_DEFAULT);
        if(page.getAllCount() % page.getPageRowSize() == 0)
        	page.setAllPageCount(page.getAllCount() / page.getPageRowSize());
        else
        	page.setAllPageCount(page.getAllCount() / page.getPageRowSize() + 1);
        if(page.getCurrentPage() >= page.getAllPageCount())
        	page.setCurrentPage(page.getAllPageCount());
        page.setBeginIndex((page.getCurrentPage() - 1) * page.getPageRowSize() > 0 ? (page.getCurrentPage() - 1) * page.getPageRowSize() : 0);
        page.setEndIndex((page.getBeginIndex() + page.getPageRowSize()) - 1);
    }
    
	public int getBeginIndex() {
		return beginIndex;
	}
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public int getAllCount() {
		return allCount;
	}
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageRowSize() {
		return pageRowSize;
	}
	public void setPageRowSize(int pageRowSize) {
		this.pageRowSize = pageRowSize;
	}
	public int getAllPageCount() {
		return allPageCount;
	}
	public void setAllPageCount(int allPageCount) {
		this.allPageCount = allPageCount;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	public static void main(String[] args) {
		PageQuery page = new PageQuery();
		page.setCurrentPage(2);
		page.setPageRowSize(20);
		page.calcutePage(page);
		System.out.println(page.getBeginIndex());
		System.out.println(page.getPageRowSize());
		
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
