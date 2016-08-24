'use strict';
const yeoman = require('yeoman-generator');
const chalk = require('chalk');
const mkdirp = require('mkdirp');
const yosay = require('yosay');

module.exports = yeoman.Base.extend({
  initializing: function () {
    this.props = {};
  },
  prompting: function () {
    // Have Yeoman greet the user.
    this.log(yosay(
      'Welcome to the polished ' + chalk.red('Kotlin Android Boilerplate from @Plastix') + ' generator!'
    ));

    const prompts = [
      {
        name: 'name',
        message: 'What are you calling your app?',
        validate: function (input) {
          if (/^([a-zA-Z0-9_]*)$/.test(input)) {
            return true;
          }
          return 'Your application name cannot contain special characters or a blank space, using the default name instead : ' + this.appname;
        },
        default: this.appname // Default to current folder name
      },
      {
        name: 'package',
        message: 'What package will you be publishing the app under?',
        validate: function (input) {
          if (/^([a-z_]{1}[a-z0-9_]*(\.[a-z_]{1}[a-z0-9_]*)*)$/.test(input)) {
            return true;
          }
          return 'The package name you have provided is not a valid Java package name.';
        },
        default: 'in.boilerplate.sample'
      },
      {
        name: 'targetSdk',
        message: 'What Android SDK will you be targeting?',
        store: true,
        default: 24
      },
      {
        name: 'minSdk',
        message: 'What is the minimum Android SDK you wish to support?',
        store: true,
        default: 16
      }
    ];

    return this.prompt(prompts).then(function (props) {
      this.props.appPackage = props.package;
      this.appName = props.name;
      this.appPackage = props.package;
      this.androidTargetSdkVersion = props.targetSdk;
      this.androidMinSdkVersion = props.minSdk;
    }.bind(this));
  },

  writing: function () {
    var packageDir = this.props.appPackage.replace(/\./g, '/');

    mkdirp('app');
    mkdirp('app/src/main/kotlin/' + packageDir);
    mkdirp('app/src/unitTests/kotlin/' + packageDir);

    this.copy('gitignore', '.gitignore');
    this.copy('build.gradle', 'build.gradle');
    this.copy('gradle.properties', 'gradle.properties');
    this.copy('gradlew', 'gradlew');
    this.copy('gradlew.bat', 'gradlew.bat');
    this.copy('settings.gradle', 'settings.gradle');
    this.copy('signing.properties.sample', 'signing.properties.sample');
    this.copy('README.md', 'README.md');
    this.copy('app/gitignore', 'app/.gitignore');
    this.copy('app/proguard-rules.pro', 'app/proguard-rules.pro');

    this.directory('gradle', 'gradle');
    this.directory('art', 'art');
    this.directory('app/src/main/res', 'app/src/main/res');

    this.template('app/build.gradle', 'app/build.gradle');
    this.template('app/src/main/AndroidManifest.xml', 'app/src/main/AndroidManifest.xml');
    this.template('app/src/main/kotlin/io/github/plastix/kotlinboilerplate', 'app/src/main/kotlin/' + packageDir, this, {});
    this.template('app/src/main/res/layout', 'app/src/main/res/layout', this, {});
    this.template('app/src/unitTests/kotlin/io/github/plastix/kotlinboilerplate', 'app/src/unitTests/kotlin/' + packageDir, this, {});
  }
});
