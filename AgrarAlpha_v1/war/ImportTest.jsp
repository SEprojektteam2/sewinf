<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
 
<%
BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<html>
<head>
<title>Tweet My Picture</title>
</head>
<body>
<h1>Tweet My Picture</h1>
<hr/>
<h2>Upload a picture and send an automatic Tweet</h2>
<form action="<%= blobstoreService.createUploadUrl("/ImportHandler") %>" method="post" enctype="multipart/form-data">
File :
<input type="text" name="filename"/>
<input type="file" name="myTweetPic"/>
<input type="submit" value="Upload Picture"/>
</form>
</html>