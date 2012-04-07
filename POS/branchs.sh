importBranchFile(){
  branchList=$1/branch.lst

  errorMsg=""
  if ! test -e ${branchList}; then
    echo "支店定義ファイルが存在しません"
    exit 1
  fi

  while read line;
  do
    branch=(`echo ${line}  | tr -s ',' ' '`)
    str=`echo ${branch[0]} | sed 's/[0-9]//g'`

    if test ${#branch[@]} -ne 2 -o ${#branch[0]} -ne 3 -o -n "${str}"; then
      echo "支店定義ファイルのフォーマットが不正です"
      exit 1
    fi
  done < ${branchList}
}

getBranchList(){
  echo ${branchList}
}
