<#import "parts/common.ftlh" as c>

<@c.page>
    <#list news as content>
        <div>
            <a>${content.id}</a>
            <span>${content.title}</span>
            <i>${content.content}</i>
        </div>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Add new comment
        </a>
        <div class="collapse" id="collapseExample">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <form action="/comment/create/${content.id}" method="post" class="form-inline">
                        <input type="text" name="comment" placeholder="Введите комментарий" class="form-control mr-2"/>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </form>
                </div>
            </div>
        </div>
    </#list>

    <#list comments as comment>

        <div>
            <a>${comment.id}</a>
            <span>${comment.comment}</span>
            <i>${comment.username}</i>
            <a href="editcomment?id=${comment.id}">edit</a>
        </div>

    </#list>

</@c.page>