package vn.edu.iuh.fit.decisions;
import java.io.File;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.apache.logging.log4j.util.Strings;
//import com.google.common.base.Strings;

public class ListClassess {
    public static void listClasses(File projectDir) {
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path,
                                                                        file) -> {
            System.out.println(path);
            System.out.println(Strings.repeat("=", path.length()));
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                        super.visit(n, arg);
                        System.out.println(" * " + n.getName());
                    }
                }.visit(StaticJavaParser.parse(file), null);
                System.out.println(); // empty line
            } catch (Exception e) {
                new RuntimeException(e);
            }
        }).explore(projectDir);
    }
    public static void main(String[] args) {
        File projectDir = new File("D:\\Dai_Hoc\\NAM4\\Kiem_Truc_Phan_Mem\\THUC_HANH\\VoNguyenThanhTu_20014641_KTTKPM_week3");
        listClasses(projectDir);
    }
}
