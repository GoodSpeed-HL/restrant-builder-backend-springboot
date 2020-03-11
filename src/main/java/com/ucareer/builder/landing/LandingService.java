package com.ucareer.builder.landing;

import com.ucareer.builder.landing.models.*;
import com.ucareer.builder.landing.repos.LandingRepository;
import com.ucareer.builder.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("LandingService")
public class LandingService {

    @Autowired
    LandingRepository landingRepository;


    public Landing initLanding(User user) {
        Landing newLanding = new Landing();

        newLanding.setName("My first landing");
        newLanding.setUser(user);

        //  builderRepository.saveAndFlush(b);

        Head h = new Head();
        h.setLanding(newLanding);
        h.setTitle("head title");
        h.setDescription("head desc");
        h.setImgUrl("https://via.placeholder.com/150");

        //  headRepository.save(h);


        Gallery g = new Gallery();
        g.setLanding(newLanding);
        g.setTitle("g title");
        g.setDescription("g desc");


        // galleryRepository.saveAndFlush(g);

        GalleryItem gi = new GalleryItem();
        gi.setGallery(g);
        gi.setTitle("g1 title");
        gi.setDescription("g1 desc");
        gi.setImageUrl("g1 image url");

        GalleryItem g2 = new GalleryItem();
        g2.setGallery(g);
        g2.setTitle("g2 title");
        g2.setDescription("g2 desc");
        g2.setImageUrl("g2 image url");


        Set<GalleryItem> itemsSet = new HashSet<GalleryItem>();
        itemsSet.add(gi);
        itemsSet.add(g2);

        g.setGalleryItem(itemsSet);

        //  galleryRepository.saveAndFlush(g);

        newLanding.setHead(h);
        newLanding.setGallery(g);

        Menu m = new Menu();
        m.setDescription("menu desc");
        m.setTitle("menu title");
        m.setLanding(newLanding);

        Set<MenuItem> menuItems = new HashSet<>();
        MenuItem mi1 = new MenuItem();
        mi1.setCategory("breakfast");
        mi1.setDescription("menu item 1 desc");
        mi1.setName("mi1");
        mi1.setPrice("100");

        MenuItem mi2 = new MenuItem();
        mi2.setCategory("breakfast");
        mi2.setDescription("menu item 2 desc");
        mi2.setName("mi2");
        mi2.setPrice("50");

        MenuItem mi3 = new MenuItem();

        mi3.setCategory("dinner");
        mi3.setDescription("menu item 3 desc");
        mi3.setName("mi3");
        mi3.setPrice("30");

        MenuItem mi4 = new MenuItem();
        mi4.setCategory("lunch");
        mi4.setDescription("menu item 4 desc");
        mi4.setName("mi4");
        mi4.setPrice("40");

        mi1.setMenu(m);
        mi2.setMenu(m);
        mi3.setMenu(m);
        mi4.setMenu(m);

        menuItems.add(mi1);
        menuItems.add(mi2);
        menuItems.add(mi3);
        menuItems.add(mi4);

        m.setMenuItems(menuItems);
        newLanding.setMenu(m);

        user.setLanding(newLanding);

        return landingRepository.save(newLanding);

    }


    public Landing updateLanding(Landing landing, User user) {
        Landing oldLanding = user.getLanding();

        oldLanding.setName(landing.getName());

        Head h = oldLanding.getHead();

        h.setImgUrl(landing.getHead().getImgUrl());
        h.setDescription(landing.getHead().getDescription());
        h.setTitle(landing.getHead().getTitle());

        Gallery g = oldLanding.getGallery();
        g.setDescription(landing.getGallery().getDescription());
        g.setTitle(landing.getGallery().getTitle());

        if (landing.getGallery().getGalleryItem().size() > 0) {
            g.getGalleryItem().clear();
            for (GalleryItem item : landing.getGallery().getGalleryItem()) {
                item.setGallery(g);
                g.getGalleryItem().add(item);
            }
            //g.setGalleryItem(itemsSet);
        }

        Menu m = oldLanding.getMenu();
        m.setTitle(landing.getMenu().getTitle());
        m.setDescription(landing.getMenu().getDescription());

        if (landing.getMenu().getMenuItems().size() > 0) {
            m.getMenuItems().clear();
            for (MenuItem item : landing.getMenu().getMenuItems()) {
                item.setMenu(m);
                m.getMenuItems().add(item);
            }
            //m.setMenuItems(itemsSet);
        }
        return landingRepository.save(oldLanding);
    }
}
