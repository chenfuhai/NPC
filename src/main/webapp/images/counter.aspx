
var str = "";
var countlen =7;
var sCountValue = "1170018";
var showmode=1;
var counterstyle="12";
for(var i = 0;sCountValue.length<=countlen;i++)
{
	sCountValue = "0" + sCountValue;
}
if(showmode == 1)
{ 
	for(var i=0;i<sCountValue.length;i++)
	{
		str += "<img src='/tj/images/" + counterstyle + "/" + sCountValue.substr(i,1) + ".gif' border='0' align='absmiddle'>";
	}
}
else
{  
	str =sCountValue;
}
document.write(str);