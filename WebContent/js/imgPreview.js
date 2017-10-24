function imgPreview (shower,getter){
	this.shower = shower;
	this.getter = getter;

	this.initElements();
	this.initEvents();
}
imgPreview.prototype={
	contructor:imgPreview,

	initElements:function  (argument) {
		this.checker = /fakepath/;
	},

	initEvents:function  (argument) {
		var that = this;
		this.getter.onchange=function  (argument) {
			if (that.checker.test(this.value)) {//检查是否IE浏览器
				//若不是IE浏览器
				if(!/image\/\w+/.test(that.getter.files[0].type)){  
    				alert("请确保文件为图像类型");  
    				return false;  
				} 
				var fr = new FileReader();
				fr.readAsDataURL(that.getter.files[0]);
				fr.onload = function (e){
					that.shower.src = e.target.result;
				}	
			}else{//若是IE浏览器
				if (/\.jpg$|\.png$|\.gif$/i.test(this.value)) {
					that.shower.src = this.value;
				}else{
					alert('wrong fomat of pic');
					return false;
				}
			}
		}
	}
}