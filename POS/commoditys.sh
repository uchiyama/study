importCommodityFile(){
  commodityList=$1/commodity.lst

  if ! test -e ${commodity}; then
    echo "商品定義ファイルが存在しません"
    exit 1
  fi

  while read line;
  do
    commodity=(`echo ${line} | tr -s ',' ' '`)
    str=`echo commodity[0] | sed 's/[A-z0-9]//g'`
    if test ${#commodity[@]} -ne 2 -o ${#commodity[0]} -ne 8 -o -n "${str}"; then
      echo "商品定義ファイルのフォーマットが不正です"
      exit 1
    fi
  done < ${commodityList}
}

getCommodityList(){
  echo ${commodityList}
}
