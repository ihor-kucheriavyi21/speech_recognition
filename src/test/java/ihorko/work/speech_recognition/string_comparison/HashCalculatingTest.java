package ihorko.work.speech_recognition.string_comparison;

import ihorko.work.speech_recognition.common.StringSearch;
import org.junit.Test;

public class HashCalculatingTest {

    private final StringSearch stringSearch = new StringSearch();


    @Test
    public void testCircleHash() {
        int average = 0;
        for (int i = 0; i < 10; i++) {
            long before = System.nanoTime();
            System.out.println("is found CIRCLE = " + stringSearch.search("consectetur", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut tellus."));
            long timeExecution = System.nanoTime() - before;
            System.out.println(timeExecution);
            average += timeExecution;
        }

        System.out.println("Average is - " +average/10);
    }

    @Test
    public void testCircleHash1000Elements(){
        int average = 0;

        for (int i = 0; i < 1000; i++) {
            long before = System.nanoTime();
            stringSearch.search("Sed imperdiet accumsan gravida.", stringWith1000Words);
            long timeExecution = System.nanoTime() - before;
            average += timeExecution;
        }

        System.out.println("Average is - " +average/10);
    }

    private static final String stringWith1000Words = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur nec viverra elit, in bibendum dui. Cras id metus eget ligula fringilla elementum. Etiam porta, est ac imperdiet molestie, libero ex consectetur purus, vitae ultricies ipsum mi a dolor. Praesent facilisis turpis lectus, mollis tempus neque tempor et. Maecenas dapibus orci a pharetra varius. Sed scelerisque nunc quam, quis dictum sem viverra quis. Nullam vel libero non velit dignissim rhoncus et non erat. Vestibulum ut quam non nunc accumsan aliquet. Phasellus dignissim ultricies tortor, eleifend cursus neque faucibus in. Cras fringilla aliquet nisi.\n" +
            "\n" +
            "Morbi dui leo, sodales et justo non, aliquam bibendum ante. Donec elit sem, blandit ac tempor et, malesuada et nisl. Duis tempus erat a dignissim molestie. Fusce auctor lacus sed odio scelerisque tempor. Nullam dictum tempus nunc ac finibus. Suspendisse efficitur orci vel libero eleifend elementum. Donec odio est, ultrices euismod nisl varius, gravida tempor enim. Duis vel placerat odio. Nam scelerisque leo vel aliquet posuere. Ut aliquet lacus a sapien laoreet accumsan. Aliquam faucibus sapien vitae molestie fermentum. Vivamus ut magna vitae diam euismod ultricies ac a nisi. Proin mollis ligula mi, non dictum magna gravida vitae. Maecenas quis finibus quam, ut pharetra metus. Cras consequat lectus a nulla ornare, ac tincidunt sem dictum.\n" +
            "\n" +
            "Mauris pellentesque nibh velit, id efficitur dui tempus eu. Mauris aliquam sed mi scelerisque cursus. Pellentesque tempor a felis et condimentum. Sed accumsan fermentum massa et laoreet. Integer lectus sem, ultricies vel egestas ut, efficitur eu mi. Curabitur molestie facilisis risus, quis dapibus nibh aliquet et. Vestibulum sit amet lectus tristique, varius nisl id, maximus nibh. Sed sed tortor magna. Vivamus volutpat rhoncus elit non faucibus. Aliquam a orci non lectus commodo sagittis. Morbi pellentesque nibh at ornare rutrum. Ut fringilla aliquam est sed vulputate. Curabitur accumsan ultrices nisi ac vehicula. Suspendisse interdum lorem sem, non tincidunt velit ornare ut.\n" +
            "\n" +
            "Vivamus condimentum nisl quis mauris consectetur volutpat. Mauris tempor dolor ut ornare hendrerit. Nulla dictum tincidunt eros. Donec at massa dui. Ut ut commodo mi. Morbi vitae ex efficitur, congue tortor sed, hendrerit massa. Donec at luctus nisl. Aliquam lobortis aliquet erat mollis condimentum. Etiam dignissim non felis sit amet cursus.\n" +
            "\n" +
            "Morbi eget tincidunt felis. Pellentesque et eros mattis, dapibus ex vel, mattis sapien. Suspendisse ornare leo metus, vel consectetur justo aliquam sed. Sed ut quam augue. Vivamus faucibus sed tortor a sodales. Integer tempor malesuada ante, sed dignissim augue accumsan ac. Nullam interdum cursus maximus.\n" +
            "\n" +
            "In eget pretium ante, nec aliquet neque. Mauris eros mi, sagittis vel ultricies id, suscipit nec odio. Integer eleifend ultricies rhoncus. Curabitur porttitor molestie velit, scelerisque sollicitudin massa sodales vel. Quisque vitae lectus dolor. Cras ultrices vulputate nisi, eu porta ex congue in. Suspendisse facilisis mi vel quam vehicula faucibus. Nullam nulla purus, iaculis vitae bibendum non, sagittis in nisi. Proin lacus velit, lacinia ut maximus a, tincidunt et sem. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce varius, dolor eget commodo semper, sapien dolor tempor nunc, vel vulputate purus ipsum a mauris.\n" +
            "\n" +
            "Donec a cursus tortor, elementum posuere turpis. Sed in sapien condimentum, sodales urna id, placerat ligula. Etiam sagittis turpis a nisl elementum, in interdum diam volutpat. Aenean egestas metus leo, id auctor nisl vehicula ut. Etiam vestibulum, elit tempor lobortis malesuada, leo felis sodales leo, nec cursus ante est sit amet magna. Integer imperdiet, est in blandit faucibus, risus nisl venenatis nunc, quis tincidunt dolor elit quis magna. Mauris nisi erat, pulvinar eu mi sit amet, ultrices scelerisque risus. Suspendisse hendrerit commodo purus, sed vehicula quam sagittis sed. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nullam ac dui fermentum magna dapibus fringilla vel ac sem. Donec efficitur, lectus nec bibendum rhoncus, ligula est facilisis eros, quis sollicitudin arcu arcu et velit. Integer aliquet convallis nibh, sit amet euismod mauris eleifend quis. Suspendisse ultricies ex ut leo suscipit consequat. Integer quis sapien et tortor scelerisque interdum. Quisque ut elit libero. Aliquam ante diam, interdum at sem et, porta sollicitudin ex.\n" +
            "\n" +
            "Nulla ante ipsum, fermentum vitae laoreet sed, accumsan vestibulum tortor. Morbi risus nisi, vulputate id porta vel, mattis vel mauris. Aliquam id risus lorem. Ut in arcu non lacus pharetra ullamcorper. Pellentesque dolor magna, dictum ac lectus non, tincidunt vestibulum lorem. Quisque laoreet tempus purus at mattis. Integer efficitur lacus eu nibh mattis porta. Ut vitae tellus sed erat sollicitudin venenatis. Proin viverra porta nulla, vitae mollis eros ornare sit amet. Morbi dapibus lectus eu lacinia rutrum. Fusce sollicitudin consectetur libero, eget fermentum arcu bibendum quis.\n" +
            "\n" +
            "Ut egestas enim venenatis metus convallis, nec faucibus quam porta. Sed imperdiet accumsan gravida. Cras mollis quam erat, vitae malesuada quam consequat nec. Nullam vel augue vel quam feugiat aliquet id et risus. Pellentesque aliquam magna at convallis congue. Mauris sed est pellentesque, lobortis nulla eu, fermentum risus. Pellentesque molestie sapien eget magna pulvinar imperdiet. Praesent quis libero ante.\n" +
            "\n" +
            "Phasellus elementum sed nulla eget vehicula. Quisque mattis eget arcu vitae tristique. Quisque eget augue nec elit ultrices cursus. Curabitur vitae odio feugiat, egestas elit non, imperdiet justo. Pellentesque nisi lacus, convallis sit amet purus sed, faucibus malesuada turpis. Sed eu feugiat felis. Sed quis placerat mi. Praesent eu imperdiet metus. Integer semper nisl vitae congue ullamcorper. Duis quis condimentum purus. Etiam sed purus ultricies, viverra sem quis, facilisis risus. Nulla arcu mauris, semper at nisl non, condimentum interdum augue. Vestibulum nec accumsan nulla. Proin ligula sem, tincidunt a risus ac, tempor rutrum risus. Suspendisse sit amet ipsum congue neque venenatis tincidunt.\n" +
            "\n" +
            "Nulla pulvinar, sapien nec pulvinar consequat, tellus mauris congue lacus, id ullamcorper lacus est pulvinar elit. Maecenas vestibulum augue in mi tempus tempus. Integer ornare dui eu magna imperdiet viverra. Cras risus turpis, consectetur nec ante sit amet, aliquam laoreet felis. Donec nec aliquet nunc. Pellentesque vulputate lectus quis porta molestie. Vivamus iaculis viverra venenatis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam hendrerit aliquam eros quis tincidunt. Mauris ultricies molestie leo, et placerat diam semper ut. Sed sed dolor volutpat, fermentum ligula eu.";
}
