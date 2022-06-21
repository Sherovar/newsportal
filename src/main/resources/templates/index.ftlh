<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form group col-md-6">
            <form action="/content/create" method="post" class="form-inline">
                <input type="text" name="content" placeholder="Введите новость" class="form-control mr-2">
                <input type="text" name="title" placeholder="Тайтл" class="form-control">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary">Добавить</button>
            </form>
        </div>
    </div>
    <a href="/content/news"> List of news</a>
</@c.page>
