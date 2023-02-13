package Service;

import DAO.SocialDAO;

public class SocialService {
    SocialDAO socialDAO;

    public SocialService(){
        socialDAO = new SocialDAO();
    }
    public SocialService(SocialDAO socialDAO){
        this.socialDAO = socialDAO;
    }
}