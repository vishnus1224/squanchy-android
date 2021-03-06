package net.squanchy.tweets

import android.support.v7.app.AppCompatActivity
import net.squanchy.injection.ActivityLifecycle
import net.squanchy.injection.ApplicationComponent
import net.squanchy.navigation.NavigationModule
import net.squanchy.navigation.Navigator
import net.squanchy.tweets.service.TwitterRepository
import net.squanchy.tweets.service.TwitterService

import dagger.Component
import net.squanchy.injection.ActivityContextModule
import net.squanchy.injection.applicationComponent

internal fun twitterComponent(activity: AppCompatActivity): TwitterComponent {
    return DaggerTwitterComponent.builder()
        .applicationComponent(activity.applicationComponent)
        .activityContextModule(ActivityContextModule(activity))
        .twitterModule(TwitterModule())
        .navigationModule(NavigationModule())
        .build()
}

@ActivityLifecycle
@Component(
        modules = arrayOf(TwitterModule::class, NavigationModule::class),
        dependencies = arrayOf(ApplicationComponent::class)
)
internal interface TwitterComponent {

    fun repository(): TwitterRepository

    fun service(): TwitterService

    fun navigator(): Navigator
}
