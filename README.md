# leanring-mall
商城项目学习



### web-admin

登录账号：admin  | Root.123456



Swagger默认访问地址

```sql
http://localhost:8081/swagger-ui.html
```

```json
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"tokenHead": "Bearer",
		"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE2ODM5NDkyNDYxMTcsImV4cCI6MTY4NDU1NDA0Nn0.l08eLCaqAsXZSn0BsDtf0xglYHAAXo-LTVpL1J-ieA9cSI_YX826zEr3KXfGzfXa6QmonERxc2hHX4C1TKURjw"
	}
}
```

由于我们的接口进行了权限认证，在调用接口的时候需要传入token，swagger进行了token的`Api key authorization`的设置。我们可以通过登录后将token设置进去。
