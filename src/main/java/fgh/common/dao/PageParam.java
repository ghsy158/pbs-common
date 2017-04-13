package fgh.common.dao;

public class PageParam {

	/**
	 * 起始页码
	 */
	private int page = 1;

	/**
	 * 每页显示条数
	 */
	private int limit;
	/**
	 * 默认为10条
	 */
	public static final int PAGE_SIZE = 10;
	/**
	 * 排序字段
	 */
	private String sort;
	/**
	 * asc or desc
	 */
	private String order;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return order;
	}

	public void setDir(String dir) {
		this.order = dir;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
