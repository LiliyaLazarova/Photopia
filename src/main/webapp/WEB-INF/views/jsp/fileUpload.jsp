<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload file</title>
<script type="text/javascript">
function Validatebodypanelbumper(theForm)
{
   var regexp;
   var extension = new FormData(theForm).get("file").value.lastIndexOf('.');
   if ((extension.toLowerCase() != ".gif") &&
       (extension.toLowerCase() != ".jpg") &&
       (extension != ""))
   {
      alert("The \"FileUpload\" field contains an unapproved filename.");
      theForm.file.focus();
      return false;
   }
   return true;
}
</script>
</head>
<body>
<div>
		<form method="POST" enctype="multipart/form-data" onsubmit="Validatebodypanelbumper()">
			<table>
				<tr><td>File to upload:</td><td>
				<input type="file" id="file" name="file" accept="image/*" /></td></tr>
				<tr><td></td><td><input type="submit" value="Upload" /></td></tr>
			</table>
		</form>
	</div>

</body>
</html>