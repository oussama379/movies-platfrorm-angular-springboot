#!/usr/bin/env node

      import {createRequire as __cjsCompatRequire} from 'module';
      const require = __cjsCompatRequire(import.meta.url);
      const __ESM_IMPORT_META_URL__ = import.meta.url;
    
import {
  parseCommandLineOptions
} from "../chunk-ICPLY7FO.js";
import {
  mainNgcc
} from "../chunk-DGTSPJIN.js";
import "../chunk-HMWNYAAE.js";
import "../chunk-I4BG3CAN.js";
import "../chunk-NDIOQ2EH.js";
import "../chunk-J6AVV3MN.js";
import "../chunk-NJ2FQOJT.js";
import "../chunk-5FZBUSFV.js";
import "../chunk-56O2PTWU.js";
import "../chunk-XR6BVLNN.js";
import "../chunk-CLRZAXXE.js";
import "../chunk-4NRCP3Y6.js";
import "../chunk-RCXOJZDO.js";
import "../chunk-XYNRD7NE.js";

// bazel-out/darwin_arm64-fastbuild/bin/packages/compiler-cli/ngcc/main-ngcc.mjs
process.title = "ngcc";
var startTime = Date.now();
var options = parseCommandLineOptions(process.argv.slice(2));
(async () => {
  try {
    await mainNgcc(options);
    if (options.logger) {
      const duration = Math.round((Date.now() - startTime) / 1e3);
      options.logger.debug(`Run ngcc in ${duration}s.`);
    }
    process.exitCode = 0;
  } catch (e) {
    console.error(e.stack || e.message);
    process.exit(typeof e.code === "number" ? e.code : 1);
  }
})();
/**
 * @license
 * Copyright Google LLC All Rights Reserved.
 *
 * Use of this source code is governed by an MIT-style license that can be
 * found in the LICENSE file at https://angular.io/license
 */
//# sourceMappingURL=main-ngcc.js.map
