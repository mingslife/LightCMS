var Util = {
	systemName: "LightCMS",
	htmlEncode: function(str) {
		var s = "";
		if (str.length == 0) return "";
		s = str.replace(/&/g, "&gt;");
		s = s.replace(/</g, "&lt;");
		s = s.replace(/>/g, "&gt;");
		s = s.replace(/ /g, "&nbsp;");
		s = s.replace(/\'/g, "&#39;");
		s = s.replace(/\"/g, "&quot;");
		s = s.replace(/\n/g, "<br />");
		return s;
	},
	setTitle: function(title) {
		document.title = title + " | " + this.systemName;
	},
	arrayParam: function(key, array) {
		if (!array) return "";
		var result = "";
		for (var i = 0, length = array.length; i < length; i++) result += key + "=" + array[i] + "&";
		return result.substring(0, result.length - 1);
	},
	bytesToSize: function(bytes) {
		if (!bytes && bytes !== 0) return '';
		if (bytes === 0) return '0 B';
		var k = 1024,
			sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
			i = Math.floor(Math.log(bytes) / Math.log(k));
	    return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
	},
	formatDate: function(data) {
		var date = new Date(data);
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var day = date.getDate();
		var dateText = year + "-" + (month > 9 ? "" : "0") + month + "-" + (day > 9 ? "" : "0") + day;
		return dateText;
	},
	formatDatetime: function(data) {
		var date = new Date(data);
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var day = date.getDate();
		var hour = date.getHours();
		var minute = date.getMinutes();
		var second = date.getSeconds();
		var dateText = year + "-" + (month > 9 ? "" : "0") + month + "-" + (day > 9 ? "" : "0") + day + " " + (hour > 9 ? "" : "0") + hour + ":" + (minute > 9 ? "" : "0") + minute + ":" + (second > 9 ? "" : "0") + second;
		return dateText;
	},
	fileInfo: function(fileSystem) {
		var fileType;
		if (fileSystem.isDirectory) fileType = "文件夹";
		switch (fileSystem.contentType) {
		case "text/plain": fileType = "文本文档"; break;
		case "application/msword": case "application/vnd.openxmlformats-officedocument.wordprocessingml.document": fileType = "Microsoft Word 文档"; break;
		case "application/vnd.ms-excel": case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet": fileType = "Microsoft Excel 工作表"; break;
		case "application/vnd.ms-powerpoint": case "application/vnd.openxmlformats-officedocument.presentationml.presentation": fileType = "Microsoft PowerPoint 幻灯片"; break;
		case "application/pdf": fileType = "PDF 文件"; break;
		case "image/bmp": case "image/gif": case "image/jpeg": case "image/png": fileType = "图片"; break;
		case "application/zip": case "application/x-gzip": case "application/x-tar": case "application/x-rar-compressed": fileType = "压缩文件"; break;
		case "audio/x-wav": case "audio/mpeg": case "audio/basic": case "audio/mid": fileType = "音频"; break;
		case "video/x-msvideo": case "video/mp4": case "video/mpeg": case "video/vnd.rn-realvideo": case "video/quicktime": case "audio/x-pn-realaudio": fileType = "视频"; break;
		case "application/x-javascript": case "text/html": case "text/css": case "text/javascript": fileType = "代码"; break;
		default: fileType = "未知文件"; break;
		}
		var dateText = this.formatDatetime(fileSystem.uploadDate);
		return fileSystem.fileName + "\n类型: " + fileType + (fileSystem.isDirectory ?  "" : "\n大小: " + this.bytesToSize(fileSystem.fileSize)) + "\n创建时间: " + dateText;
	},
	fileIcon: function(fileSystem) {
		if (fileSystem.isDirectory) return "fa fa-folder";
		switch (fileSystem.contentType) {
		case "text/plain": return "fa fa-file-text-o";
		case "application/msword": case "application/vnd.openxmlformats-officedocument.wordprocessingml.document": return "fa fa-file-word-o";
		case "application/vnd.ms-excel": case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet": return "fa fa-file-excel-o";
		case "application/vnd.ms-powerpoint": case "application/vnd.openxmlformats-officedocument.presentationml.presentation": return "fa fa-file-powerpoint-o";
		case "application/pdf": return "fa fa-file-pdf-o";
		case "image/bmp": case "image/gif": case "image/jpeg": case "image/png": return "fa fa-file-image-o";
		case "application/zip": case "application/x-gzip": case "application/x-tar": case "application/x-rar-compressed": return "fa fa-file-archive-o";
		case "audio/x-wav": case "audio/mpeg": case "audio/basic": case "audio/mid": return "fa fa-file-audio-o";
		case "video/x-msvideo": case "video/mp4": case "video/mpeg": case "video/vnd.rn-realvideo": case "video/quicktime": case "audio/x-pn-realaudio": return "fa fa-file-movie-o";
		case "application/x-javascript": case "text/html": case "text/css": case "text/javascript": return "fa fa-file-code-o";
		default: return "fa fa-file-o";
		}
	},
	enumFormatter: function(enums, value) {
		for (var i = 0, length = enums.length; i < length; i++) {
			var temp = enums[i];
			if (temp.value === value) return temp.key;
		}
		return "(ERROR)";
	}
};
