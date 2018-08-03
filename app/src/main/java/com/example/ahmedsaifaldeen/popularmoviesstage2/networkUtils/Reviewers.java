package com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Reviewers {
    /**
     * author : Reno
     * content : Well, one off from two of this year's most expected movies alongside 'The Battle of Five Armies'. Like all the Chris Nolan fans, I was equally excited to see the movie on the opening day opening show. But I slightly disappointed that it was not a digital 3D film. I agree, this science-fiction was more dramatized than usual space travel stories does with an adventure-thriller. Almost a 3 hour long stretch movie did not waste much time to take us to the core of the story. Get prepared for this extremely rare voyage into the space with a logical explanation for everything you see on the screen. Well done research for the most matured and intelligent writing. All the credit must go to Nolan brothers. A good sign from Jonathan Nolan, who can make big in the entertainment industry in a future like his brother.
     * <p>
     * As we know many had liked 'Inception', to me that was a simple multi-layered action movie, that's all. But the same stuffs that used in this film makes sense. In fact, you have to have a little knowledge over how the universe works, so then it will be easy to catch the scene and situations while watching the movie. Totally like a documentary style concept, but with the additional stuffs like characters and its emotions add flavor that gives a movie look. An educational movie, though it also can work for those who wants just entertainment. Only the slow movie pace would test their patience.
     * <p>
     * It all begins like Shyamalan's 'Signs' movie with a family living surrounded by corn field. Then switches to 'The Astronaut Farmer' and going through 'Gravity', finally meets the 'Inception'. It was just a reference to call the movie setting that brings constant change for every half an hour. Like the opening scene and the end scene had over a 100 years difference.
     * <p>
     * As I earlier said it was the story of a family alongside the future of humankind and decoding universal mysteries through travelling in space and time. This movie would a reasonable for those who thought '2001: A Space Odyssey' is a boring piece, Cleverly written cinematic piece especially for science geeks. I don't know how perfect the movie to the actual present astrophysics, but will justify for the common people's capacity of understanding with an encourageable amount of commercial elements in it to entertain as well.
     * <p>
     * The first three quarters of the movie was well made. It puts me in a unblinkable position like a story was narrated by Brian Cox in a television series. Like I said, lots of astrophysics involved in it, but strangely human emotions were also exhibited equally that I never expected one from Nolan film. It was not an ordinary sentiment, but was strong enough to make a man cry for happy and sad situations in the movie. I liked science and emotion coming together. In fact, it saved the movie, otherwise it would have been a science documentary straight from NASA production through Nolan direction.
     * <p>
     * There are many surprise elements in the movie and of course there is a twist at the end. We can call it a series of twists like the layers. Compared to last quarter of the movie to the rest, it completely detaches which opens broadly to the different directions. And that happens so fast rushing towards the other end. Which give an impression of the movie 'Inception'. In a perfect way to say the first 75% was 'The Tree of Life' and the remains are 'Inception'.
     * <p>
     * ‘‘This world's a treasure,
     * but it's been telling us to leave for a while now.’’
     * <p>
     * All the actors were so good. Matthew McConaughey steals the show as he dominates the majority of the screen space in the story presentation. There's no ruling out the fine performance executions from Anne Hathaway and the young star from the Twilight movie, Mackenzie Foy. The remaining cast was having less scope which were like the guest appearances that was widened a little broader, but was perfectly fitted for the story. Especially Matt Damon's was the crucial one.
     * <p>
     * Remember the movie 'Contact', a lovely movie, which was ruined by its fictional ending. Something like that happened in this film as well. The story was initiated with a realistic approach with actual scientific contents as per the present understanding about the universe. But the end was let me down with the layered contents that kind of impossible to agree with it. As a cinematic theme it worked, yeah, a good solution for this wonderfully written story. We know that the time can't run backwards, so that's the trouble.
     * <p>
     * Anyway, this movie defines in a new way, I mean scientifically the existence of ghost. It was not a horror movie, but I liked supernatural force that merged with this science fiction theme. That explains and gives vast ideas to expand our physics beyond something and somewhere yet to reach. Hats off to the director, because he was not thinking of making money here. His idea was to implement what the humans are understood so far about the cosmos. And he very nicely transformed those into the silver screen with the blend of human emotions. In my opinion, this will replace '2001: A Space Odyssey' for sometime till another one make this way.
     * <p>
     * I could have not asked a better space travel drama than this, especially when I heard Nolan doing a science-fiction I believed he gonna rock it. He was so true to the science and the human feelings in this film. If you had seen enough movies before like this one, you can recall your memories like the Tom Hanks parts from 'Cloud Atlas'. But still independently stands strong and falls in a never seen before category.
     * <p>
     * ‘‘Maybe we've spent too long
     * trying to figure all this out with theory.’’
     * <p>
     * The end scene leaves a hint of a possible sequel. I would be happy if that happen in a near future, but definitely that would be a completely different cinema as per how this one ended. I know his fans want that to happen and so am I.
     * <p>
     * The visuals were not that great, but simply very good. To see those in digital 3D would have given us a different experience, sadly Nolan was not in favor of that technology. Hoping this movie would get as many as the Oscars nod. Especially not getting into the best motion picture shortlist would be a shame. Like I said I'm no one fan, I just love watching everyone's every movie. I would have went to see it again if it was converted into digital 3D, since I'm modern tech geek when it comes to the films.
     * <p>
     * It will become a talk of the week, perhaps month all over the world, so don't leave behind when your friends talk about it. What I gonna say is it is a must see asap if you are a movie fanatic like me otherwise Nolan movies does not need anyone's recommendation because his movies usually sell itself like the hot samosa.
     * id : 5463856c0e0a267815002598
     * url : https://www.themoviedb.org/review/5463856c0e0a267815002598
     */

    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String content;
    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;

    public static List<Reviewers> arrayResultsBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Reviewers>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
