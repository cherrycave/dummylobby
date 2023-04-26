package net.cherrycave.dummylobby;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.apache.maven.artifact.repository.Authentication;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.util.repository.AuthenticationBuilder;
import org.jetbrains.annotations.NotNull;

public class DummyLobbyPluginLoader implements PluginLoader {
    @Override
    public void classloader(@NotNull PluginClasspathBuilder classpathBuilder) {
        MavenLibraryResolver resolver = new MavenLibraryResolver();

        resolver.addDependency(new Dependency(new DefaultArtifact("net.axay:kspigot:1.19.2"), null));
        resolver.addDependency(new Dependency(new DefaultArtifact("net.cherrycave:birgid:0.2.0"), null));

        resolver.addRepository(new RemoteRepository.Builder("maven central", "default", "https://repo.maven.apache.org/maven2/").build());
        var authentication = new AuthenticationBuilder().addUsername("stckoverflw").addPassword("O3OJSlysptIRrNyLQoIODQ8WJdaxOrCYYVo0X7bu3yeY8FnRP5iemu4gju87f4aC").build();
        resolver.addRepository(new RemoteRepository.Builder("private repo", "default", "https://maven.stckoverflw.net/private").setAuthentication(authentication).build());

        classpathBuilder.addLibrary(resolver);
    }
}
