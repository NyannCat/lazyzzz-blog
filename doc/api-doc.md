# Api Documentation


**简介**:Api Documentation


**HOST**:http://localhost:8080


**联系人**:


**Version**:1.0


**接口路径**:/v3/api-docs


[TOC]






# article-controller


## 根据id查找文章


**接口地址**:`/api/admin/article`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Article对象0|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|categoryId|文章类型外键|integer(int32)|integer(int32)|
|comments|评论数量|integer(int64)|integer(int64)|
|coverPage|文章封面url|string||
|createTime|文章创建时间|string(date-time)|string(date-time)|
|formatContent|格式化后文章内容(HTML)|string||
|id||integer(int32)|integer(int32)|
|likes|点赞数量|integer(int64)|integer(int64)|
|originalContent|原始文章内容(Markdown)|string||
|shares|分享数量|integer(int64)|integer(int64)|
|summary|文章预览|string||
|title|文章标题|string||
|type|0:公开,1:私密|integer(int32)|integer(int32)|
|updateTime|文章更新时间|string(date-time)|string(date-time)|
|views|访问数量|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"categoryId": 0,
	"comments": 0,
	"coverPage": "",
	"createTime": "",
	"formatContent": "",
	"id": 0,
	"likes": 0,
	"originalContent": "",
	"shares": 0,
	"summary": "",
	"title": "",
	"type": 0,
	"updateTime": "",
	"views": 0
}
```


## 创建文章


**接口地址**:`/api/admin/article`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "categoryId": 0,
  "coverPage": "",
  "formatContent": "",
  "id": 0,
  "originalContent": "",
  "summary": "",
  "title": "",
  "type": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|文章上传模型|文章上传模型|body|true|文章上传模型|文章上传模型|
|&emsp;&emsp;categoryId|文章分类id||false|integer(int32)||
|&emsp;&emsp;coverPage|文章封面||false|string||
|&emsp;&emsp;formatContent|文章HTML内容||false|string||
|&emsp;&emsp;id|文章id||false|integer(int32)||
|&emsp;&emsp;originalContent|文章Markdown内容||false|string||
|&emsp;&emsp;summary|文章简介||false|string||
|&emsp;&emsp;title|文章标题||false|string||
|&emsp;&emsp;type|文章类型, 0:公开,1:私密||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


## 更新文章


**接口地址**:`/api/admin/article`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "categoryId": 0,
  "coverPage": "",
  "formatContent": "",
  "id": 0,
  "originalContent": "",
  "summary": "",
  "title": "",
  "type": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|query|true|integer(int32)||
|文章上传模型|文章上传模型|body|true|文章上传模型|文章上传模型|
|&emsp;&emsp;categoryId|文章分类id||false|integer(int32)||
|&emsp;&emsp;coverPage|文章封面||false|string||
|&emsp;&emsp;formatContent|文章HTML内容||false|string||
|&emsp;&emsp;id|文章id||false|integer(int32)||
|&emsp;&emsp;originalContent|文章Markdown内容||false|string||
|&emsp;&emsp;summary|文章简介||false|string||
|&emsp;&emsp;title|文章标题||false|string||
|&emsp;&emsp;type|文章类型, 0:公开,1:私密||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


## 删除文章


**接口地址**:`/api/admin/article`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


## 分页查询文章列表


**接口地址**:`/api/admin/article/all`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|page|query|false|integer(int32)||
|limit|limit|query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|IPage«Article对象»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|current||integer(int64)|integer(int64)|
|hitCount||boolean||
|pages||integer(int64)|integer(int64)|
|records||array|Article对象|
|&emsp;&emsp;categoryId|文章类型外键|integer(int32)||
|&emsp;&emsp;categoryName||string||
|&emsp;&emsp;comments|评论数量|integer(int64)||
|&emsp;&emsp;coverPage|文章封面url|string||
|&emsp;&emsp;createTime|文章创建时间|string(date-time)||
|&emsp;&emsp;formatContent|格式化后文章内容(HTML)|string||
|&emsp;&emsp;id||integer(int32)||
|&emsp;&emsp;likes|点赞数量|integer(int64)||
|&emsp;&emsp;originalContent|原始文章内容(Markdown)|string||
|&emsp;&emsp;shares|分享数量|integer(int64)||
|&emsp;&emsp;summary|文章预览|string||
|&emsp;&emsp;title|文章标题|string||
|&emsp;&emsp;type|0:公开,1:私密|integer(int32)||
|&emsp;&emsp;updateTime|文章更新时间|string(date-time)||
|&emsp;&emsp;views|访问数量|integer(int64)||
|searchCount||boolean||
|size||integer(int64)|integer(int64)|
|total||integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"categoryId": 0,
			"categoryName": "",
			"comments": 0,
			"coverPage": "",
			"createTime": "",
			"formatContent": "",
			"id": 0,
			"likes": 0,
			"originalContent": "",
			"shares": 0,
			"summary": "",
			"title": "",
			"type": 0,
			"updateTime": "",
			"views": 0
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```


# attachment-controller


## 分页查询附件


**接口地址**:`/api/admin/attachment`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|page|query|false|integer(int32)||
|size|size|query|false|integer(int32)||
|keyword|keyword|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|IPage«Attachment对象»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|current||integer(int64)|integer(int64)|
|hitCount||boolean||
|pages||integer(int64)|integer(int64)|
|records||array|Attachment对象|
|&emsp;&emsp;attachmentType|文件存储类型(0：本地，1：七牛云),可用值:LOCAL,QI_NIU_YUN|string||
|&emsp;&emsp;fileKey|oss或本地文件key，用于删除文件|string||
|&emsp;&emsp;id||integer(int32)||
|&emsp;&emsp;mediaType|文件类型|string||
|&emsp;&emsp;name|文件名称|string||
|&emsp;&emsp;path|文件路径|string||
|searchCount||boolean||
|size||integer(int64)|integer(int64)|
|total||integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"attachmentType": "",
			"fileKey": "",
			"id": 0,
			"mediaType": "",
			"name": "",
			"path": ""
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```


## 上传附件


**接口地址**:`/api/admin/attachment`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json,application/octet-stream`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|type|type,可用值:LOCAL,QI_NIU_YUN|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|文件上传返回结果|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|filePath|文件路径|string||
|filename|文件名|string||
|height|高度|integer(int32)|integer(int32)|
|key|文件唯一编码|string||
|mediaType|文件类型|MediaType|MediaType|
|&emsp;&emsp;charset||Charset|Charset|
|&emsp;&emsp;&emsp;&emsp;registered||boolean||
|&emsp;&emsp;concrete||boolean||
|&emsp;&emsp;parameters||object||
|&emsp;&emsp;qualityValue||number(double)||
|&emsp;&emsp;subtype||string||
|&emsp;&emsp;subtypeSuffix||string||
|&emsp;&emsp;type||string||
|&emsp;&emsp;wildcardSubtype||boolean||
|&emsp;&emsp;wildcardType||boolean||
|size|文件大小|integer(int64)|integer(int64)|
|width|宽度|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
	"filePath": "",
	"filename": "",
	"height": 0,
	"key": "",
	"mediaType": {
		"charset": {
			"registered": true
		},
		"concrete": true,
		"parameters": {},
		"qualityValue": 0,
		"subtype": "",
		"subtypeSuffix": "",
		"type": "",
		"wildcardSubtype": true,
		"wildcardType": true
	},
	"size": 0,
	"width": 0
}
```


## 删除附件


**接口地址**:`/api/admin/attachment`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**响应示例**:

```javascript
{
	"empty": true,
	"model": {},
	"modelMap": {},
	"reference": true,
	"status": "",
	"view": {
		"contentType": ""
	},
	"viewName": ""
}
```


# captcha-controller


## 获取二维码


**接口地址**:`/api/captcha`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


暂无


**响应示例**:
```javascript

```


# category-controller


## 返回全部分类


**接口地址**:`/api/admin/category`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Category对象|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|id||integer(int32)|integer(int32)|
|name|类型名称|string||


**响应示例**:
```javascript
[
	{
		"id": 0,
		"name": ""
	}
]
```


## 创建新的分类


**接口地址**:`/api/admin/category`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|name|name|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


## 修改分类


**接口地址**:`/api/admin/category`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|query|true|integer(int32)||
|name|name|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


## 删除分类


**接口地址**:`/api/admin/category`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


# comment-admin-controller


## 评论列表


**接口地址**:`/api/admin/comment`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|page|query|true|integer(int32)||
|size|size|query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|IPage«Comment对象»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|current||integer(int64)|integer(int64)|
|hitCount||boolean||
|pages||integer(int64)|integer(int64)|
|records||array|Comment对象|
|&emsp;&emsp;articleId|文章外键引用|integer(int32)||
|&emsp;&emsp;content|评论内容|string||
|&emsp;&emsp;createTime|评论创建时间|string(date-time)||
|&emsp;&emsp;email|评论人邮箱|string||
|&emsp;&emsp;id||integer(int32)||
|&emsp;&emsp;isAdmin|是否为管理员评论0:不是,1:是|integer(int32)||
|&emsp;&emsp;nickname|评论人昵称|string||
|&emsp;&emsp;parentId|评论父级引用|integer(int32)||
|searchCount||boolean||
|size||integer(int64)|integer(int64)|
|total||integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"current": 0,
	"hitCount": true,
	"pages": 0,
	"records": [
		{
			"articleId": 0,
			"content": "",
			"createTime": "",
			"email": "",
			"id": 0,
			"isAdmin": 0,
			"nickname": "",
			"parentId": 0
		}
	],
	"searchCount": true,
	"size": 0,
	"total": 0
}
```


## 管理员回复评论


**接口地址**:`/api/admin/comment`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "articleId": 0,
  "content": "",
  "parentId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|管理员评论参数|管理员评论参数|body|true|管理员评论参数|管理员评论参数|
|&emsp;&emsp;articleId|文章ID||false|integer(int32)||
|&emsp;&emsp;content|评论内容||false|string||
|&emsp;&emsp;parentId|父级评论ID||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


## 删除评论


**接口地址**:`/api/admin/comment`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


# comment-controller


## 发布评论


**接口地址**:`/api/comment`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "articleId": 0,
  "captcha": "",
  "content": "",
  "email": "",
  "nickname": "",
  "parentId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|评论参数|评论参数|body|true|评论参数|评论参数|
|&emsp;&emsp;articleId|文章ID||false|integer(int32)||
|&emsp;&emsp;captcha|验证码||false|string||
|&emsp;&emsp;content|评论内容||false|string||
|&emsp;&emsp;email|评论人邮箱||false|string||
|&emsp;&emsp;nickname|评论人昵称||false|string||
|&emsp;&emsp;parentId|父级评论ID||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


# index-controller


## index


**接口地址**:`/`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


暂无


**响应示例**:
```javascript

```


## admin


**接口地址**:`/admin`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|model||query|false|object||
|reference||query|false|boolean||
|status|枚举类型,可用值:100 CONTINUE,101 SWITCHING_PROTOCOLS,102 PROCESSING,103 CHECKPOINT,200 OK,201 CREATED,202 ACCEPTED,203 NON_AUTHORITATIVE_INFORMATION,204 NO_CONTENT,205 RESET_CONTENT,206 PARTIAL_CONTENT,207 MULTI_STATUS,208 ALREADY_REPORTED,226 IM_USED,300 MULTIPLE_CHOICES,301 MOVED_PERMANENTLY,302 FOUND,302 MOVED_TEMPORARILY,303 SEE_OTHER,304 NOT_MODIFIED,305 USE_PROXY,307 TEMPORARY_REDIRECT,308 PERMANENT_REDIRECT,400 BAD_REQUEST,401 UNAUTHORIZED,402 PAYMENT_REQUIRED,403 FORBIDDEN,404 NOT_FOUND,405 METHOD_NOT_ALLOWED,406 NOT_ACCEPTABLE,407 PROXY_AUTHENTICATION_REQUIRED,408 REQUEST_TIMEOUT,409 CONFLICT,410 GONE,411 LENGTH_REQUIRED,412 PRECONDITION_FAILED,413 PAYLOAD_TOO_LARGE,413 REQUEST_ENTITY_TOO_LARGE,414 REQUEST_URI_TOO_LONG,414 URI_TOO_LONG,415 UNSUPPORTED_MEDIA_TYPE,416 REQUESTED_RANGE_NOT_SATISFIABLE,417 EXPECTATION_FAILED,418 I_AM_A_TEAPOT,419 INSUFFICIENT_SPACE_ON_RESOURCE,420 METHOD_FAILURE,421 DESTINATION_LOCKED,422 UNPROCESSABLE_ENTITY,423 LOCKED,424 FAILED_DEPENDENCY,425 TOO_EARLY,426 UPGRADE_REQUIRED,428 PRECONDITION_REQUIRED,429 TOO_MANY_REQUESTS,431 REQUEST_HEADER_FIELDS_TOO_LARGE,451 UNAVAILABLE_FOR_LEGAL_REASONS,500 INTERNAL_SERVER_ERROR,501 NOT_IMPLEMENTED,502 BAD_GATEWAY,503 SERVICE_UNAVAILABLE,504 GATEWAY_TIMEOUT,505 HTTP_VERSION_NOT_SUPPORTED,506 VARIANT_ALSO_NEGOTIATES,507 INSUFFICIENT_STORAGE,508 LOOP_DETECTED,509 BANDWIDTH_LIMIT_EXCEEDED,510 NOT_EXTENDED,511 NETWORK_AUTHENTICATION_REQUIRED|query|false|string||
|view.contentType||query|false|string||
|viewName||query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ModelAndView|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|empty||boolean||
|model||object||
|modelMap||object||
|reference||boolean||
|status|可用值:ACCEPTED,ALREADY_REPORTED,BAD_GATEWAY,BAD_REQUEST,BANDWIDTH_LIMIT_EXCEEDED,CHECKPOINT,CONFLICT,CONTINUE,CREATED,DESTINATION_LOCKED,EXPECTATION_FAILED,FAILED_DEPENDENCY,FORBIDDEN,FOUND,GATEWAY_TIMEOUT,GONE,HTTP_VERSION_NOT_SUPPORTED,IM_USED,INSUFFICIENT_SPACE_ON_RESOURCE,INSUFFICIENT_STORAGE,INTERNAL_SERVER_ERROR,I_AM_A_TEAPOT,LENGTH_REQUIRED,LOCKED,LOOP_DETECTED,METHOD_FAILURE,METHOD_NOT_ALLOWED,MOVED_PERMANENTLY,MOVED_TEMPORARILY,MULTIPLE_CHOICES,MULTI_STATUS,NETWORK_AUTHENTICATION_REQUIRED,NON_AUTHORITATIVE_INFORMATION,NOT_ACCEPTABLE,NOT_EXTENDED,NOT_FOUND,NOT_IMPLEMENTED,NOT_MODIFIED,NO_CONTENT,OK,PARTIAL_CONTENT,PAYLOAD_TOO_LARGE,PAYMENT_REQUIRED,PERMANENT_REDIRECT,PRECONDITION_FAILED,PRECONDITION_REQUIRED,PROCESSING,PROXY_AUTHENTICATION_REQUIRED,REQUESTED_RANGE_NOT_SATISFIABLE,REQUEST_ENTITY_TOO_LARGE,REQUEST_HEADER_FIELDS_TOO_LARGE,REQUEST_TIMEOUT,REQUEST_URI_TOO_LONG,RESET_CONTENT,SEE_OTHER,SERVICE_UNAVAILABLE,SWITCHING_PROTOCOLS,TEMPORARY_REDIRECT,TOO_EARLY,TOO_MANY_REQUESTS,UNAUTHORIZED,UNAVAILABLE_FOR_LEGAL_REASONS,UNPROCESSABLE_ENTITY,UNSUPPORTED_MEDIA_TYPE,UPGRADE_REQUIRED,URI_TOO_LONG,USE_PROXY,VARIANT_ALSO_NEGOTIATES|string||
|view||View|View|
|&emsp;&emsp;contentType||string||
|viewName||string||


**响应示例**:
```javascript
{
	"empty": true,
	"model": {},
	"modelMap": {},
	"reference": true,
	"status": "",
	"view": {
		"contentType": ""
	},
	"viewName": ""
}
```


## archive


**接口地址**:`/archive`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


暂无


**响应示例**:
```javascript

```


## article


**接口地址**:`/article/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


暂无


**响应示例**:
```javascript

```


## index


**接口地址**:`/page`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|page|query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


暂无


**响应示例**:
```javascript

```


## search


**接口地址**:`/search`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|keyword|keyword|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


暂无


**响应示例**:
```javascript

```


## search


**接口地址**:`/search/page`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|keyword|keyword|query|true|string||
|page|page|query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


暂无


**响应示例**:
```javascript

```


# install-controller


## 检测博客是否已经安装


**接口地址**:`/api/admin/install`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


## 博客安装


**接口地址**:`/api/admin/install`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "avatar": "",
  "description": "",
  "email": "",
  "password": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|用户dto对象|用户dto对象|body|true|用户dto对象|用户dto对象|
|&emsp;&emsp;avatar|头像||false|string||
|&emsp;&emsp;description|头像||false|string||
|&emsp;&emsp;email|邮箱||false|string||
|&emsp;&emsp;password|明文密码||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


# statistics-controller


## dashboard


**接口地址**:`/api/admin/statistics/dashboard`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


## logs


**接口地址**:`/api/admin/statistics/logs`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|page|query|true|integer(int32)||
|size|size|query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


## user


**接口地址**:`/api/admin/statistics/user`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


# user-controller


## 获取用户信息


**接口地址**:`/api/admin/user`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|token|token|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|User对象|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|avatar|头像|string||
|createTime|创建时间|string(date-time)|string(date-time)|
|description|个人简介|string||
|email|邮箱|string||
|id||integer(int32)|integer(int32)|
|updateTime|修改时间|string(date-time)|string(date-time)|
|username|用户名|string||


**响应示例**:
```javascript
{
	"avatar": "",
	"createTime": "",
	"description": "",
	"email": "",
	"id": 0,
	"updateTime": "",
	"username": ""
}
```


## 更新用户信息


**接口地址**:`/api/admin/user`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "avatar": "",
  "description": "",
  "email": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|用户更新对象|用户更新对象|body|true|用户更新对象|用户更新对象|
|&emsp;&emsp;avatar|头像||false|string||
|&emsp;&emsp;description|头像||false|string||
|&emsp;&emsp;email|邮箱||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


## 用户登录


**接口地址**:`/api/admin/user/login`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "password": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|用户登录模型|用户登录模型|body|true|用户登录模型|用户登录模型|
|&emsp;&emsp;password|明文密码||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


## 修改用户密码


**接口地址**:`/api/admin/user/password`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|oldPassword|oldPassword|query|true|string||
|newPassword|newPassword|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|Response|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|操作代码|integer(int32)|integer(int32)|
|data|数据|object||
|msg|消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"msg": ""
}
```


## 用户注册


**接口地址**:`/api/admin/user/register`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "avatar": "",
  "description": "",
  "email": "",
  "password": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|用户dto对象|用户dto对象|body|true|用户dto对象|用户dto对象|
|&emsp;&emsp;avatar|头像||false|string||
|&emsp;&emsp;description|头像||false|string||
|&emsp;&emsp;email|邮箱||false|string||
|&emsp;&emsp;password|明文密码||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK||
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


暂无


**响应示例**:
```javascript

```