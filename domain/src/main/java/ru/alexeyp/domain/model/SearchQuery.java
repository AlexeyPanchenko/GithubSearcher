package ru.alexeyp.domain.model;

public class SearchQuery {

    private String _query;
    private int _page;

    public SearchQuery(String query, int page) {
        _query = query;
        _page = page;
    }

    public String getQuery() {
        return _query;
    }

    public int getPage() {
        return _page;
    }

    public void setPage(int page) {
        _page = page;
    }
}
