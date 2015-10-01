package tequila.dao.models;

import android.database.sqlite.SQLiteDatabase;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import java.util.Map;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 *
 * @see AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig postDaoConfig;
    private final DaoConfig fieldDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig expertDaoConfig;
    private final DaoConfig orderDaoConfig;
    private final DaoConfig channelDaoConfig;
    private final DaoConfig channelTypeDaoConfig;
    private final DaoConfig tweetDaoConfig;
    private final DaoConfig tweetLikeDaoConfig;
    private final DaoConfig tweetEchoDaoConfig;

    private final PostDao postDao;
    private final FieldDao fieldDao;
    private final UserDao userDao;
    private final ExpertDao expertDao;
    private final OrderDao orderDao;
    private final ChannelDao channelDao;
    private final ChannelTypeDao channelTypeDao;
    private final TweetDao tweetDao;
    private final TweetLikeDao tweetLikeDao;
    private final TweetEchoDao tweetEchoDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        postDaoConfig = daoConfigMap.get(PostDao.class).clone();
        postDaoConfig.initIdentityScope(type);

        fieldDaoConfig = daoConfigMap.get(FieldDao.class).clone();
        fieldDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        expertDaoConfig = daoConfigMap.get(ExpertDao.class).clone();
        expertDaoConfig.initIdentityScope(type);

        orderDaoConfig = daoConfigMap.get(OrderDao.class).clone();
        orderDaoConfig.initIdentityScope(type);

        channelDaoConfig = daoConfigMap.get(ChannelDao.class).clone();
        channelDaoConfig.initIdentityScope(type);

        channelTypeDaoConfig = daoConfigMap.get(ChannelTypeDao.class).clone();
        channelTypeDaoConfig.initIdentityScope(type);

        tweetDaoConfig = daoConfigMap.get(TweetDao.class).clone();
        tweetDaoConfig.initIdentityScope(type);

        tweetLikeDaoConfig = daoConfigMap.get(TweetLikeDao.class).clone();
        tweetLikeDaoConfig.initIdentityScope(type);

        tweetEchoDaoConfig = daoConfigMap.get(TweetEchoDao.class).clone();
        tweetEchoDaoConfig.initIdentityScope(type);

        postDao = new PostDao(postDaoConfig, this);
        fieldDao = new FieldDao(fieldDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        expertDao = new ExpertDao(expertDaoConfig, this);
        orderDao = new OrderDao(orderDaoConfig, this);
        channelDao = new ChannelDao(channelDaoConfig, this);
        channelTypeDao = new ChannelTypeDao(channelTypeDaoConfig, this);
        tweetDao = new TweetDao(tweetDaoConfig, this);
        tweetLikeDao = new TweetLikeDao(tweetLikeDaoConfig, this);
        tweetEchoDao = new TweetEchoDao(tweetEchoDaoConfig, this);

        registerDao(Post.class, postDao);
        registerDao(Field.class, fieldDao);
        registerDao(User.class, userDao);
        registerDao(Expert.class, expertDao);
        registerDao(Order.class, orderDao);
        registerDao(Channel.class, channelDao);
        registerDao(ChannelType.class, channelTypeDao);
        registerDao(Tweet.class, tweetDao);
        registerDao(TweetLike.class, tweetLikeDao);
        registerDao(TweetEcho.class, tweetEchoDao);
    }

    public void clear() {
        postDaoConfig.getIdentityScope().clear();
        fieldDaoConfig.getIdentityScope().clear();
        userDaoConfig.getIdentityScope().clear();
        expertDaoConfig.getIdentityScope().clear();
        orderDaoConfig.getIdentityScope().clear();
        channelDaoConfig.getIdentityScope().clear();
        channelTypeDaoConfig.getIdentityScope().clear();
        tweetDaoConfig.getIdentityScope().clear();
        tweetLikeDaoConfig.getIdentityScope().clear();
        tweetEchoDaoConfig.getIdentityScope().clear();
    }

    public PostDao getPostDao() {
        return postDao;
    }

    public FieldDao getFieldDao() {
        return fieldDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public ExpertDao getExpertDao() {
        return expertDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public ChannelDao getChannelDao() {
        return channelDao;
    }

    public ChannelTypeDao getChannelTypeDao() {
        return channelTypeDao;
    }

    public TweetDao getTweetDao() {
        return tweetDao;
    }

    public TweetLikeDao getTweetLikeDao() {
        return tweetLikeDao;
    }

    public TweetEchoDao getTweetEchoDao() {
        return tweetEchoDao;
    }

}
