import pandas as pd

def solution(info, query):
    
    n = len(info)
    m = len(query)
    
    my_data = [[]]*n
    result = [0]*m
    
    lang = {'cpp':0,'java':1,'python':2}
    jds = {'backend':0,'frontend':1}
    sj = {'senior':0,'junior':1}
    sf = {'chicken':0,'pizza':1}
    q_temp = [lang,jds,sj,sf]
    
    for idx,participant in enumerate(info):
        l,j,s,f,n = participant.split()
        my_data[idx] = [lang[l],jds[j],sj[s],sf[f],int(n)]
    
    df = pd.DataFrame(my_data, columns=['language','jd','is_senior','soul_food','score'])
    

    for idx,q in enumerate(query):
        q_info = q.split()[::2]
        q_info.append(q.split()[-1])
        
        q_col = ["language","jd","is_senior","soul_food","score"]
        q_lst = ['']*5
        
        for i in range(4):
            if q_info[i]!='-':
                q_lst[i] = f'({q_col[i]} == {q_temp[i][q_info[i]]})'
        q_lst[4] = f'({q_col[4]} >= {int(q_info[4])})'

        
        q_str = ''
        for df_query in q_lst:
            if df_query and q_str:
                q_str += ' & ' + df_query 
            elif df_query and not q_str:
                q_str += df_query
        
        selected_df = df.query(q_str)
        
        result[idx] = len(selected_df)
        
        
    return result