<#import "parts/common.ftlh" as c>

<#assign
user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
name = user.getUsername()
isAdmin = user.isAdmin()
>
<@c.page>
    <div class="card">
    <#list news as content>
        <div class="card my-3">
            <div class="m-2">
                <a href="/content/${content.id}">${content.title}</a>
            </div>
            <#if content.uploadedFile??>
                <img src="/img/${content.filename}" class="card-img-top">
            </#if>
            <div class="card-footer text-muted">
                ${content.content}
            </div>
        </div>
        <#if name == content.username || isAdmin>
            <a class="btn btn-primary" data-toggle="collapse" href="#collapseEditNews" role="button"
               aria-expanded="false"
               aria-controls="collapseEditNews">
                Edit news
            </a>
            <div class="collapse" id="collapseEditNews">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form action="/content/edit/${content.id}" method="post" class="form-inline">
                            <input type="text" name="newContent" placeholder="Enter news" value="${content.content}"
                                   class="form-control mr-2"/>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-primary">Edit</button>
                        </form>
                        <form action="/content/delete/${content.id}" method="post" class="form-inline">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-primary">Delete</button>
                        </form>
                    </div>
                </div>
            </div>
        </#if>
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

    <#list news as content>
        <#list comments as comment>
            <div>
                <a>${comment.id}</a>
                <span>${comment.comment}</span>
                <i>${comment.username}</i>
                <#if name == comment.username || isAdmin>
                    <a class="btn btn-primary" data-toggle="collapse" href="#collapseEdit" role="button"
                       aria-expanded="false"
                       aria-controls="collapseEdit">
                        Edit comment
                    </a>
                    <div class="collapse" id="collapseEdit">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <form action="/comment/edit/${comment.id}" method="post" class="form-inline">
                                    <input type="text" name="newComment" placeholder="Введите комментарий" value="${comment.comment}"
                                           class="form-control mr-2"/>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <button type="submit" class="btn btn-primary">Edit</button>
                                </form>
                                <form action="/comment/delete/${comment.id}" method="post" class="form-inline">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <button type="submit" class="btn btn-primary">Delete</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </#if>
                <b></b>
            </div>
        </#list>
    </#list>

</@c.page>