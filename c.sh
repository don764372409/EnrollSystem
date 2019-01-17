#!/bin/bash
#
dbUser='root'
dbPasswd='sola'
dbHost='39.108.187.254'
dbName='enroll'
filePath='./data2013-2017.csv'


declare -A provinces
declare -A universitys
declare -A majors
declare -A batchs



readfile(){
	while read  line
	do
		eval `echo "$line" | awk -F'\t' '{printf("province=%s;university=%s;time=%s;major=%s;batch=%s;maxNum=%s;minNum=%s;avgNum=%s;num=%s;minRank=%s\n",$1,$2,$3,$4,$5,$6,$7,$8,$9,$10)}'`
	

		if [ -z "${provinces[$province]}" ]
		then
			local	pId=`mysql -u$dbUser -p$dbPasswd -D$dbName -e "SELECT id FROM t_province WHERE name='$province'" | tail -1`
			if [ -n "$pId" ]
			then
				provinces[$province]=$pId
			else
				continue
				#provinces[$province]=`mysql -u$dbUser -p$dbPasswd -D$dbName -e "BEGIN;INSERT INTO t_province(name) VALUES('$province');SELECT id FROM t_province WHERE name='$province';COMMIT"`
			fi
		fi

		if [ -z "${universitys[$university]}" ]
		then
			local	uId=`mysql -u$dbUser -p$dbPasswd -D$dbName -e "SELECT id FROM t_university WHERE name='$university'" | tail -1`
			if [ -n "$uId" ]
			then
				universitys[$university]=$uId
			else
				universitys[$university]=`mysql -u$dbUser -p$dbPasswd -D$dbName -e "BEGIN;INSERT INTO t_university(pId,name) VALUES(${provinces[$province]},'$university');SELECT id FROM t_university WHERE name='$university';COMMIT" | tail -1`
			fi
		fi
	
		if [ -z "${majors[$major]}" ]
		then
			#mysql -u$dbUser -p$dbPasswd -D$dbName -e "INSERT INTO t_major VALUES('$major')" | tail -1`
			local mId=`mysql -u$dbUser -p$dbPasswd -D$dbName -e "SELECT id FROM t_major WHERE name='$major'" | tail -1`
			if [ -n "$mId" ]
			then
				majors[$major]=$mId
			else
				majors[$major]=`mysql -u$dbUser -p$dbPasswd -D$dbName -e "BEGIN;INSERT INTO t_major(name,remark,majorExplain,ranking) VALUES('$major',' ',' ',0);SELECT id FROM t_major WHERE name='$major';COMMIT" | tail -1`
			fi
		fi
	
		if [ -z "${batchs[$batch]}"  ]
		then
			local	bId=`mysql -u$dbUser -p$dbPasswd -D$dbName -e "SELECT id FROM t_batch WHERE name='$batch'" | tail -1`
			if [ -n "$bId" ]
			then
				batchs[$batch]=$bId
			else
				batchs[$batch]=`mysql -u$dbUser -p$dbPasswd -D$dbName -e "BEGIN;INSERT INTO t_batch(name) VALUES('$batch');SELECT id FROM t_batch WHERE name='$batch';COMMIT" | tail -1`
			fi
		fi

		if [ $num == "-" ]
		then
			num=0
		fi
		if [ $maxNum == "-" ]
		then
			maxNum=0
		fi
		if [ $minNum == "-" ]
		then
			minNum=0
		fi
		if [ $avgNum == "-" ]
		then
			avgNum=0
		fi
		if [ $minRank == "-" ]
		then
			minRank=0
		fi
		mysql -u$dbUser -p$dbPasswd -D$dbName -e "INSERT INTO t_enroll(uId,pId,mId,bId,number,maxNumber,minNumber,avgNumber,maxRanking,minRanking,avgRanking,time,tuition,studyYear) VALUES(${universitys[$university]},${provinces[$province]},${majors[$major]},${batchs[$batch]},$num,$maxNum,$minNum,$avgNum,0,$minRank,0,$time,0,0)"
	done < $filePath
}

startDate=`date +%s%N`
readfile
endDate=`date +%s%N`
echo $[(endDate-startDate)/100000]
#echo ${provinces[@]}
#echo ${!provinces[@]}
#echo -e "\n\n"
#

#echo ${universitys[@]}
#echo ${!universitys[@]}
#
#echo -e "\n\n"
#echo ${majors[@]}
#echo ${!majors[@]}
#echo -e "\n\n"
#echo ${batchs[@]}
#echo ${!batchs[@]}

