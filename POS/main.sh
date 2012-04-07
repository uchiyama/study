#!/bin/sh
IFS_BACKUP=${IFS}
set -e

source ./branchs.sh
importBranchFile $1
branchs=$(getBranchList)
source ./commoditys.sh
importCommodityFile $1
commoditys=$(getCommodityList)
IFS='
'
recordFiles=(`find $1 -name *.rcd`)

#支店売り上げ
{
while read branch;
do
  branchArray=(`echo ${branch}  | tr -s ',' '\n'`)
  branchAmount=0
  for i in `seq 1 ${#recordFiles[@]}`
  do
    j=0
    
    while read record;
    do
      salesArray[${j}]=`echo ${record} | tr -d '\r'`
      j=(`expr ${j} + 1`)
    done < ${recordFiles[`expr ${i} - 1`]}
    if test ${branchArray[0]} == ${salesArray[0]}; then
       branchAmount=`expr ${branchAmount} + ${salesArray[2]}`
    fi
  done
  echo `echo ${branch} | tr -d '\r'`,${branchAmount}
done < ${branchs}
}>$1/branch.out

#商品売り上げ
{
while read commodity;
do
  commodityArray=(`echo ${commodity}  | tr -s ',' '\n'`)
  commodityAmount=0
  for i in `seq 1 ${#recordFiles[@]}`
  do
    j=0
    
    while read record;
    do
      salesArray[${j}]=`echo ${record} | tr -d '\r'`
      j=(`expr ${j} + 1`)
    done < ${recordFiles[`expr ${i} - 1`]}
    if test ${commodityArray[0]} == ${salesArray[1]}; then
       commodityAmount=`expr ${commodityAmount} + ${salesArray[2]}`
    fi
  done
  echo `echo ${commodity} | tr -d '\r'`,${commodityAmount}
done < ${commoditys}
}>$1/commodity.out
IFS=${IFS_BACKUP}
