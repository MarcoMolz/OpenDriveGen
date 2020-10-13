import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    int n = 60;
    final int length = 2000;
    int curr = -100;

    public static void main(String[] args) throws IOException {
        new Main().start();
        System.out.println("Done.");
    }

    public Main(){}

    public void start() throws IOException {

        System.out.println("Generating "+n*length+" meters of 3-lane straight road.");
        StringBuilder sb = new StringBuilder();
        sb.append(init());

        for (int i = 0; i<=n; i++){
            sb.append(segment(i));
        }

        sb.append(end());

        writeToFile(sb, "road.xodr");
    }

    private static void writeToFile(StringBuilder stringBuilder, String filename) throws IOException {
        File file = new File(filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(stringBuilder.toString());
        }
    }

    private static String sgen(int i){
        if (i==0) return "s000";
        int floor = (int)Math.floor(Math.log10(i))+1;
        StringBuilder sb = new StringBuilder();
        sb.append("s");
        for (int j=0; j<(3-floor) ; j++){
            sb.append("0");
        }
        sb.append(i);
        return sb.toString();


    }

    private static String init(){
        return "<?xml version=\"1.0\" standalone=\"yes\"?>\n" +
                "<OpenDRIVE>\n" +
                "    <header revMajor=\"1\" revMinor=\"1\" name=\"openDrive\" version=\"1.00\" date=\""+DateTimeFormatter.ofPattern("yyyy-MM-dd_hh-mm-ss").format(LocalDateTime.now())+"\" north=\"1.9000000000000000e+03\" south=\"-1.1500000000000000e+03\" east=\"3.3000000000000000e+03\" west=\"-4.8000000000000000e+02\">\n" +
                "    </header>";
    }

    private String segment(int i){
        String pre = "\t\t\t<predecessor elementType=\"road\" elementId=\""+sgen(i-1)+"\" contactPoint=\"end\" />\n";
        String suc = "\t\t\t<successor elementType=\"road\" elementId=\""+sgen(i+1)+"\" contactPoint=\"start\" />\n";

        if (i==0) pre = "";
        if (i==n) suc = "";


        return "<road name=\""+sgen(i)+"\" length=\""+length+".0\" id=\""+sgen(i)+"\" junction=\"-1\">\n" +
                "        <link>\n" +
                pre +
                suc +
                "        </link>\n" +
                "        <planView>\n" +
                "\t\t\t<geometry s=\"0.0\" x=\""+(curr-(i*length))+"\" y=\"-100.0\" hdg=\"3.14159\" length=\""+length+".0\">\n" +
                "\t\t\t\t<line/>\n" +
                "\t\t\t</geometry>\n" +
                "\n" +
                "        </planView>\n" +
                "        <elevationProfile>\n" +
                "            <elevation s=\"0.0000000000000000e+00\" a=\"9.5000000000000000e+00\" b=\"0.0000000000000000e+00\" c=\"0.0000000000000000e+00\" d=\"0.0000000000000000e+00\"/>\n" +
                "        </elevationProfile>\n" +
                "        <lateralProfile>\n" +
                "        </lateralProfile>\n" +
                "        <lanes>\n" +
                "            <laneSection s=\"0.0000000000000000e+00\">\n" +
                "                <left>\n" +
                "\n" +
                "\t\t\t\t\t<lane id=\"2\" type=\"driving\" level= \"false\">\n" +
                "                        <link>\n" +
                "\t\t\t\t\t\t\t<predecessor id=\"2\"/>\n" +
                "\t\t\t\t\t\t\t<successor id=\"2\"/>\n" +
                "                        </link>\n" +
                "                        <width sOffset=\"0.0000000000000000e+00\" a=\"3.0\" b=\"0.0000000000000000e+00\" c=\"0.0000000000000000e+00\" d=\"0.0000000000000000e+00\"/>\n" +
                "                        <roadMark sOffset=\"0.0000000000000000e+00\" type=\"solid\" weight=\"standard\" color=\"standard\" width=\"1.3000000000000000e-01\"/>\n" +
                "                        <speed sOffset=\"0.0000000000000000e+00\" max=\"180.0\" unit=\"km/h\" />\n" +
                "                    </lane>\n" +
                "                    <lane id=\"1\" type=\"driving\" level= \"false\">\n" +
                "                        <link>\n" +
                "\t\t\t\t\t\t\t<predecessor id=\"1\"/>\n" +
                "\t\t\t\t\t\t\t<successor id=\"1\"/>\n" +
                "                        </link>\n" +
                "                        <width sOffset=\"0.0000000000000000e+00\" a=\"3.0\" b=\"0.0000000000000000e+00\" c=\"0.0000000000000000e+00\" d=\"0.0000000000000000e+00\"/>\n" +
                "                        <roadMark sOffset=\"0.0000000000000000e+00\" type=\"broken\" weight=\"standard\" color=\"standard\" width=\"1.3000000000000000e-01\"/>\n" +
                "                        <speed sOffset=\"0.0000000000000000e+00\" max=\"180.0\" unit=\"km/h\" />\n" +
                "                    </lane>\n" +
                "                </left>\n" +
                "                <center>\n" +
                "                    <lane id=\"0\" type=\"driving\" level= \"false\">\n" +
                "                        <link>\n" +
                "                        </link>\n" +
                "                        <roadMark sOffset=\"0.0000000000000000e+00\" type=\"broken\" weight=\"standard\" color=\"standard\" width=\"1.3000000000000000e-01\"/>\n" +
                "                    </lane>\n" +
                "                </center>\n" +
                "                <right>\n" +
                "                    <lane id=\"-1\" type=\"driving\" level= \"false\">\n" +
                "                        <link>\n" +
                "\t\t\t\t\t\t\t<predecessor id=\"-1\"/>\n" +
                "\t\t\t\t\t\t\t<successor id=\"-1\"/>\n" +
                "                        </link>\n" +
                "                        <width sOffset=\"0.0000000000000000e+00\" a=\"3.0\" b=\"0.0000000000000000e+00\" c=\"0.0000000000000000e+00\" d=\"0.0000000000000000e+00\"/>\n" +
                "                        <roadMark sOffset=\"0.0000000000000000e+00\" type=\"solid\" weight=\"standard\" color=\"standard\" width=\"1.3000000000000000e-01\"/>\n" +
                "                        <speed sOffset=\"0.0000000000000000e+00\" max=\"180.0\" unit=\"km/h\" />\n" +
                "                    </lane>\n" +
                "\n" +
                "\n" +
                "                </right>\n" +
                "            </laneSection>\n" +
                "        </lanes>\n" +
                "        <objects>\n" +
                "        </objects>\n" +
                "        <signals>\n" +
                "        </signals>\n" +
                "    </road>";
    }

    private static String end(){
        return "</OpenDRIVE>";
    }
}
