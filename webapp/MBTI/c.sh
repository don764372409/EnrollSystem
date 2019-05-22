#!/bin/bash
#
declare -r dbHost='server'
#declare -r dbHost='localhost'
declare -r dbName='enroll'
declare -r dbUser='root'
declare -r dbPasswd='admin'
#declare -r dbPasswd='sola'
declare -r sql="mysql -u$dbUser -p$dbPasswd -h$dbHost -D$dbName -e"

#awk -F'\t' '{if($1 ~ "[EI][NS][TF][JP]") a=$1;if($4!="") print a" "$4}' mbti.csv > new.txt
#awk '{split($2,a,",");for(i in a) if(b[$1a[i]]==""){print $1" "a[i]; b[$1a[i]]=0}}' new.txt
#awk '{if(a[$1]++ ==0) print $1;print  "         <tr>\n           <td>"$2"</td>\n           <td>"$3"</td>\n         </tr>"}' d.txt
declare -A a

while read line
do
	eval `echo "$line" | awk '{printf("type=%s;major=%s;no=%s\n",$1,$2,$3)}'`
	if [ -z "${a[$type]}" ]
	then
		a[$type]=0
	fi

	if [ -z "${a[$type$major]}" ]
	then
		a[$type$major]=0
		if [ ${a[$type]} -le 10 ]
		then
			eval `$sql"SELECT * FROM t_major WHERE no=$no\G" | awk -F':' '{if($1=="   name"){sub(/^[[:blank:]]*/,"",$2);print "name="$2};if($1=="jianjie"){sub(/^[[:blank:]]*/,"",$2); print "jianjie=\""$2"\""};if($1==" mubiao"){sub(/^[[:blank:]]*/,"",$2); print "mubiao=\""$2"\""};if($1==" yaoqiu"){sub(/^[[:blank:]]*/,"",$2); print "yaoqiu=\""$2"\""};if($1=="kecheng"){sub(/^[[:blank:]]*/,"",$2); print "kecheng=\""$2"\""};if($1=="content") {sub(/^[[:blank:]]*/,"",$2);print "content=\""$2"\""};}'`
			if [[ $jianjie != "暂无" ]] && [[ $jianjie != "NULL" ]] 
			then
				echo "
				<div style=\"margin: 0 auto;width: 50%;text-align: center;\">
				<h3>$name</h3>
			</div>
				<b>专业信息：</b>
				<p clas=\"tip\">
						$content
			</p>
				<b>专业简介：</b>
				<p clas=\"tip\">
						$jianjie
			</p>
				<b>培养目标：</b>
				<p clas=\"tip\">
						$mubiao
			</p>
			<b>专业要求：</b>
			<p clas=\"tip\">
					$yaoqiu
			</p>
			<b>主干课程：</b>
			<p clas=\"tip\">
					$kecheng
			</p>
			</div>

			" >> ${type}.html

			a[$type]=$[${a[$type]}+1]
			fi
		fi
	fi

done < d.txt

