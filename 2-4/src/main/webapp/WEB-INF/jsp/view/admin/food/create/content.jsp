<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div class="row">
        <c:if test="${id != null}">
            <div class="py10"></div>
            <div class="food-alert cart teal lighten-2 p10 white-text">
                <div class="s-hflex weight-strong" style="font-size: 20px">
                    <div>
                        The food was ${food.id == null ? 'created' : 'updated'} successfully.
                    </div>
                    <div class="equal-flex"></div>
                    <div>
                        <a href="/food/${id}" class="blue-text text-darken-4">View</a>
                    </div>
                </div>
            </div>
        </c:if>

        <form
                action="${food.id == null ? '/admin/food/create' : '/admin/food/update/'.concat('' + food.id)}"
                method="post"
                enctype="multipart/form-data"
        >
            <div class="s-vflex">
                <div class="input-field">
                    <input type="hidden" name="id" value="${food.id}"/>
                </div>
                <div class="s-hflex">
                    <div class="input-field col s12 m9">
                        <label for="title">Title</label>
                        <input id="title" type="text" name="title" value="<c:out value="${food.title}" />" />
                    </div>
                    <div class="input-field col s12 m3">
                        <div class="input-field col s12">
                            <select id="category" name="category-id">
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.id}" ${food.categoryId == category.id ? 'selected' : ''}><c:out value="${category.title}" /></option>
                                </c:forEach>
                            </select>
                            <label for="category">Category</label>
                        </div>
                    </div>
                </div>
                <div class="input-field">
                    <label for="description">Description</label>
                    <textarea id="description" name="description" class="materialize-textarea"><c:out value="${food.description}" /></textarea>
                </div>
                <div class="input-field">
                    <label for="price">Price</label>
                    <input id="price" type="number" step="0.01" name="price" value="${food.price}" />
                </div>
                <div>
                    <input type="hidden" name="original-image-path" value="<c:out value="${food.imagePath}" />" />
                </div>
                <div class="file-field input-field">
                    <div class="btn">
                        <span>Select an image</span>
                        <input type="file" name="image" accept="image/*" />
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" name="image-path" value="<c:out value="${food.originalImagePath}" />" />
                    </div>
                </div>
                <div class="s-hflex-end">
                    <button type="submit" class="teal lighten-2 btn waves-effect waves-light">
                        ${food.id == null ? 'Create' : 'Update'}
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>