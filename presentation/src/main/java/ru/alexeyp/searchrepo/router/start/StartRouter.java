package ru.alexeyp.searchrepo.router.start;


import ru.alexeyp.searchrepo.router.Router;

public abstract class StartRouter extends Router {
    public abstract void navigateToLogin();
    public abstract void navigateToMain();
}
