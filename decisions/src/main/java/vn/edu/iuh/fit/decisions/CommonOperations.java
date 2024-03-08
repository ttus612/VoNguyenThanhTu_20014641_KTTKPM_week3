package vn.edu.iuh.fit.decisions;

import java.io.File;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.apache.logging.log4j.util.Strings;
//import com.google.common.base.Strings;

public class CommonOperations {
    public static void listMethodCalls(File projectDir) {
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level,
                                                                        path, file) -> {
            System.out.println(path);
            System.out.println(Strings.repeat("=", path.length()));
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(PackageDeclaration n, Object arg)
                    {
                        super.visit(n, arg);
                        System.out.println(n.getNameAsString());
                    }
                    @Override
                    public void visit(FieldDeclaration n, Object arg) {
                        super.visit(n, arg);
                        System.out.println(" [L " + n.getBegin() + "]" + n);
                    }
                    @Override
                    public void visit(MethodDeclaration n, Object arg) {
                        super.visit(n, arg);
                        System.out.println(" [L " + n.getBegin() + "]" + n.getDeclarationAsString());
                    }
                }.visit(StaticJavaParser.parse(file), null);
            } catch (Exception e) {
                new RuntimeException(e);
            }
        }).explore(projectDir);
    }
    public static void main(String[] args) {
        File projectDir = new File("D:\\Dai_Hoc\\NAM4\\Kiem_Truc_Phan_Mem\\THUC_HANH\\VoNguyenThanhTu_20014641_KTTKPM_week3\\spring_boot_api_jwt_ad\\src\\main\\java");
        listMethodCalls(projectDir);
    }
}
