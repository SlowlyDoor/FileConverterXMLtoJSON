package ru.vyatsu.fileconverter.core.transform;

import lombok.experimental.UtilityClass;
import ru.vyatsu.fileconverter.core.model.json.*;
import ru.vyatsu.fileconverter.core.model.xml.MangalibXml;
import ru.vyatsu.fileconverter.core.model.xml.Manhwa;
import ru.vyatsu.fileconverter.core.model.xml.TeamTranslation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@UtilityClass
public class MangalibJsonConverter {

    private int currentId = 0;

    public MangalibXml mangalibJsonToMangalib(MangalibJson mangalibJson) {
        List<Manhwa> manhwaList = new ArrayList<>();

        if (mangalibJson != null && mangalibJson.getMangalib() != null && mangalibJson.getMangalib().getAuthors() != null) {
            for (Map<String, AuthorJson> authorMap : mangalibJson.getMangalib().getAuthors()) {
                if (!authorMap.isEmpty()) {
                    Map.Entry<String, AuthorJson> entry = authorMap.entrySet().iterator().next();
                    manhwaList.addAll(authorJsonToManhwaList(entry.getValue().getName(), entry.getValue()));
                }
            }
        }

        return MangalibXml.builder().mangalib(manhwaList).build();
    }

    private List<Manhwa> authorJsonToManhwaList(String authorName, AuthorJson authorJson) {
        return authorJson.getManhws().stream()
                .map(manhwaJson -> manhwaJsonToManhwa(manhwaJson, authorName))
                .toList();
    }

    private Manhwa manhwaJsonToManhwa(ManhwaJson manhwaJson, String authorName) {
        return Manhwa.builder()
                .id(generateId())
                .title(manhwaJson.getTitle())
                .author(authorName)
                .publicationYear(manhwaJson.getPublicationYear())
                .chapters(manhwaJson.getChapters())
                .translationTeam(teamTranslationJsonToTeamTranslation(manhwaJson.getTranslationTeam()))
                .build();
    }

    private TeamTranslation teamTranslationJsonToTeamTranslation(TeamTranslationJson teamTranslationJson) {
        return TeamTranslation.builder()
                .name(teamTranslationJson.getName())
                .publicationProjects(teamTranslationJson.getPublicationProjects())
                .adminName(teamTranslationJson.getAdminName())
                .build();
    }

    private String generateId() {
        return String.valueOf(currentId++);
    }
}

