import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_25240_가희와파일탐색기2_ssu{
    //수정권한이 있다면 읽기권환도 있으므로 2,3에 R추가
    static String[] permIdx = {"","X","RW","RWX","R","RX","RW","RWX"};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int U = Integer.parseInt(st.nextToken());
        int F = Integer.parseInt(st.nextToken());

        //user 저장
        HashMap<String,User> userMap = new HashMap<>();
        while(U-->0){
            st = new StringTokenizer(br.readLine());
            String name;
            switch(st.countTokens()){
                case 1:
                    name = st.nextToken();
                    userMap.put(name,new User(name,null));
                    break;
                case 2:
                    name = st.nextToken();
                    String[] group = st.nextToken().split(",");
                    userMap.put(name,new User(name,group));
                    break;
            }
        }

        //file 저장
        HashMap<String,File> fileMap = new HashMap<>();
        while(F-->0){
            st = new StringTokenizer(br.readLine());
            String fName = st.nextToken();
            String fPerm = st.nextToken();
            String fOwner = st.nextToken();
            String fGroup = st.nextToken();
            fileMap.put(fName,new File(fName,fPerm,fOwner,fGroup));
        }

        int Q = Integer.parseInt(br.readLine());
        while(Q-->0){
            st = new StringTokenizer(br.readLine());
            String uName = st.nextToken();
            String fName = st.nextToken();
            char oper = st.nextToken().charAt(0);

            File targetFile = fileMap.get(fName);
            User targetUser = userMap.get(uName);

            // 해당 파일에 대해서 타겟 유저가 해당 명령을 수행할 수 있는지 확인
            sb.append(targetFile.canOperation(targetUser,oper)? 1 : 0).append("\n");
        }
        System.out.print(sb.toString());

    }

    static class File{
        String fName;
        String fPerm;
        String fOwner;
        String fGroup;
        String ownPerm;
        String groupPerm;
        String otherPerm;
        public File(String fName,String fPerm,String fOwner,String fGroup){
            this.fName = fName;
            this.fPerm = fPerm;
            this.fOwner = fOwner;
            this.fGroup = fGroup;
            this.setPermission();
        }

        //Owner,Group,Other별로 권한을 문자열로 저장
        public void setPermission(){
            this.ownPerm = permIdx[this.fPerm.charAt(0)-'0'];
            this.groupPerm = permIdx[this.fPerm.charAt(1)-'0'];
            this.otherPerm = permIdx[this.fPerm.charAt(2)-'0'];
        }

        public boolean canOperation(User user,char operation){
            // 소유자라면
            if(this.fOwner.equals(user.uName)){
                if(this.ownPerm.indexOf(operation)!=-1 || this.groupPerm.indexOf(operation)!=-1 || this.otherPerm.indexOf(operation)!=-1) return true;
                return false;
            }
            // 그룹에 속한다면
            else if(user.uGroup.contains(this.fGroup)){
                if(this.groupPerm.indexOf(operation)!=-1 || this.otherPerm.indexOf(operation)!=-1) return true;
                return false;
            }
            // 그 어느경우도 아니라면
            else{
                if(this.otherPerm.indexOf(operation)!=-1) return true;
                return false;
            }
        }
    }

    static class User{
        String uName;
        HashSet<String> uGroup;

        public User(String uName,String[] uGroup){
            this.uName = uName;
            this.uGroup = new HashSet<>();
            //자기 자신의 이름은 default 그룹명
            this.uGroup.add(this.uName);
            if(uGroup!=null){
                for(String groupName : uGroup) {
                    this.uGroup.add(groupName);
                }
            }
        }
    }
}