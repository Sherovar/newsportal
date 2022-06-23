<#import "parts/common.ftlh" as c>

<@c.page>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new Message
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form action="/content/create" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" name="title" placeholder="Title" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="content" placeholder="Content">
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
    <a href="/content/news"> List of news</a>

    <div class="card-columns">
        <#list news as content>
            <div class="card my-3">
                <div class="m-2">
                    <a href="/content/${content.id}">${content.title}</a>
                </div>
                <#if content.filename??>
                    <img src="/img/${content.filename}" class="card-img-top">
                </#if>
                <div class="card-footer text-muted">
                    ${content.content}
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>

</@c.page>